package com.emfldlem.Common;

import java.io.CharArrayWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtility {

    public static final int CHARSET_UTF8 = 1;
    public static final int CHARSET_NON_UTF8 = 0;
    
    public String nvl(Object obj) {
		return nvl(obj, "") ;
	}

	public String nvl(Object obj, String defaultValue) {

		if (obj != null && !"".equals(obj)) {
			return (String)obj;
		} else {
			return defaultValue;
		}
	}

	public boolean nvl(Object obj, boolean defaultValue) {

		if (obj != null && !"".equals(obj)) {
			return (Boolean)obj;
		} else {
			return defaultValue;
		}
	}

    /**
     * 문자열의 바이트수를 리턴한다.
     * TextUtil.CHARSET_UTF8 : 한글을 3바이트로 처리
     * TextUtil.CHARSET_NON_UTF8 : 한글을 2바이트로 처리
     *
     * @param value
     * @return
     */
    public long getLength(String value) {
        return getLength(value, 1);
    }

    public long getLength(String value, int charset) {

        long length = 0;

        for (int i = 0; i < value.length(); i++) {
            char chr = value.charAt(i);

            if (chr >= 0x0001 && chr <= 0x007F) {
                length++;
            } else if (chr > 0x07FF) {
                if (charset == 1) {	// 한글 UTF8에서 3바이트
                    length += 3;
                } else {
                    length += 2;
                }
            } else {
                length += 2;
            }
        }

        return length;
    }

    /**
     * 주어진 문자열을 처음위치부터 주어진 바이트 수만큼 자른다.
     * 
     * @param value
     * @param length
     * @return
     */
    public String substring(String value, long length) {

        long count = 0;

        try (CharArrayWriter writer = new CharArrayWriter()) {

            for (int i = 0; i < value.length(); i++) {

                char chr = value.charAt(i);

                if (chr >= 0x0001 && chr <= 0x007F) {
                    count++;
                } else if (chr > 0x07FF) {	// 한글 UTF에서 3바이트이므로 2바이트로 계산
                    count += 2;
                } else {
                    count += 2;
                }

                if (count > length) {
                    break;
                }

                writer.append(chr);
            }

            return writer.toString();

        }
    }

    /**
     * 원본 문자열의 앞에 패딩 문자열을 반복하여 붇여 전체 크기가 totalLength인 문자열로 반환한다. 패딩 문자열은 전체 크기에
     * 맞게 앞에서 부터 잘려서 들어가고 원본 문자열보다 전체 크기가 작을 수 없다.
     * 예) lpad("abcd", 8, "XY") --> XYXYabcd,
     * lpad("abcd", 7, "XY") --> XYXabcd
     * 
     * @param originalString
     *            원본문자열
     * @param totalLength
     *            전체크기
     * @param padString
     *            패딩문자열
     * @return string
     */
    public String lpad(String originalString, int totalLength, String padString) {
        if (originalString == null || padString == null || totalLength <= 0) {
            throw new UtilityException("Target string and padding String can't be null and totalLength must be positive number");
        }

        int originalLength = originalString.length();
        int padLength = padString.length();

        if (totalLength == originalLength) {
            return originalString;
        } else if (totalLength < originalLength) {
            throw new UtilityException("Total length must not be smaller than original string length");
        }

        int moduloLength = (totalLength - originalLength) % padLength;
        StringBuffer pad = new StringBuffer("");

        for (int i = originalLength; i < totalLength - moduloLength; i += padLength) {
            pad.append(padString);
        }

        if (moduloLength != 0) {
            pad.append(padString.substring(0, moduloLength));
        }

        return pad.append(originalString).toString();
    }

    /**
     * 원본 문자열의 뒤에 패딩 문자열을 반복하여 붇여 전체 크기가 totalLength인 문자열로 반환한다. 패딩 문자열은 전체 크기에
     * 맞게 앞에서 부터 잘려서 들어가고 원본 문자열보다 전체 크기가 작을 수 없다.
     * 예) rpad("abcd", 8, "XY") --> abcdXYXY,
     * rpad("abcd", 7, "XY") --> abcdXYX
     * 
     * @param originalString
     * @param totalLength
     * @param padString
     * @return string
     */
    public String rpad(String originalString, int totalLength, String padString) {
        if (originalString == null || padString == null || totalLength <= 0) {
            throw new UtilityException("Target string and padding String can't be null and totalLength must be positive number");
        }

        int originalLength = originalString.length();
        int padLength = padString.length();

        if (totalLength == originalLength) {
            return originalString;
        } else if (totalLength < originalLength) {
            throw new UtilityException("Total length must not be smaller than original string length");
        }

        int moduloLength = (totalLength - originalLength) % padLength;
        StringBuffer pad = new StringBuffer("");

        for (int i = originalLength; i < totalLength - moduloLength; i += padLength) {
            pad.append(padString);
        }
        if (moduloLength != 0) {
            pad.append(padString.substring(0, moduloLength));
        }

        return originalString + pad.toString();
    }

    /**
     * 작성자:박현진
     * 개요:전문등의 통신용이나 DB저장시 사용. 바이트수 기준으로 오른쪽을 fillerh 채운다. filler가 없으면 " "로 채워진다.
     */
    public byte[] rpadbyte(String value, int length, String filler) {

        if (filler == null) {
            filler = " ";	// 기본값 스페이스
        }

        int count = 0;

        for (int i = 0; i < value.length(); i++) {
            char chr = value.charAt(i);
            if (chr >= 0x0001 && chr <= 0x007F) {
                count++;
            } else if (chr > 0x07FF) {	// 한글 UTF에서 3바이트이므로 2바이트로 계산
                count += 2;
            } else {
                count += 2;
            }
        }

        StringBuffer result = new StringBuffer(value);
        for (int i = count; i < length; i++) {
            result.append(filler);
        }
        return result.toString().getBytes();
    }

    /**
     * 작성자:박현진
     * 개요:전문등의 통신용이나 DB저장시 사용. 바이트수 기준으로 왼쪽을 fillerh 채운다. filler가 없으면 " "로 채워진다.
     */
    public byte[] lpadbyte(String value, int length, String filler) {

        if (filler == null) {
            filler = " ";	// 기본값 스페이스
        }

        int count = 0;

        for (int i = 0; i < value.length(); i++) {
            char chr = value.charAt(i);
            if (chr >= 0x0001 && chr <= 0x007F) {
                count++;
            } else if (chr > 0x07FF) {	// 한글 UTF에서 3바이트이므로 2바이트로 계산
                count += 2;
            } else {
                count += 2;
            }
        }

        StringBuffer result = new StringBuffer();
        for (int i = count; i < length; i++) {
            result.append(filler);
        }
        result.append(value);
        return result.toString().getBytes();
    }

    /**
     * 주어진 문자열을 구분자(delimiter)로 나눈 문자열 배열(<code>String[]</code>)을 반환한다.
     * 
     * @return string배열
     */
    public String[] split(String str, String delimiter) {
        return split(str, delimiter, true);
    }

    public String[] split(String str, String delimiter, boolean bEmpty) {
        if (str == null) {
            return new String[0];
        }

        if (delimiter == null || delimiter.equals("")) {
            String[] ret = new String[1];
            ret[0] = str;
            return ret;
        }

        String token = null;
        if (bEmpty) {
            List<String> list = new ArrayList<>();

            int start = 0;
            int end = 0;
            while ((end = str.indexOf(delimiter, start)) != -1) {
                token = str.substring(start, end);
                list.add(token);
                // start = end + 1; // delimiter가 "#;"인 경우는?
                start = end + delimiter.length();
            }
            list.add(str.substring(start)); // !!!

            return list.toArray(new String[list.size()]);
        } else { // 공백 무시
            if (str.length() < 1) {
                return new String[0];
            }

            List<String> list = new ArrayList<>();

            int start = 0;
            int end = 0;
            while ((end = str.indexOf(delimiter, start)) != -1) {
                token = str.substring(start, end);
                if (token != null && !"".equals(token)) {
                    list.add(token);
                }
                // start = end + 1; // delimiter가 "#;"인 경우는?
                start = end + delimiter.length();
            }
            token = str.substring(start);
            if (token != null && !"".equals(token)) {
                list.add(token);
            }

            return list.toArray(new String[list.size()]);
        }
    }

    /**
     * 문자열 중의 특정 문자열을 원하는 문자열로 바꾼다.
     * 대소문자를 구분하지 않고 변환한다.
     * 
     * @param word
     * @param regex
     *            자바 정규식 표현
     * @param replacement
     * @return string
     */
    public String replaceString(String word, String regex, String replacement) {
        return replaceString(word, regex, replacement, true);
    }

    /**
     * 문자열 중의 특정 문자열을 원하는 문자열로 바꾼다.
     * caseSensitive:true -> 대소문자를 구분하지 않는다.
     * caseSensitive:false -> 대소문자를 구분한다.
     * 
     * @param word
     * @param regex
     *            자바 정규식 표현
     * @param replacement
     * @param caseSensitive
     * @return string
     */
    public String replaceString(String word, String regex, String replacement, boolean caseSensitive) {

        if (word == null) {
            throw new UtilityException("Parameter 'word' can not be null.");
        }

        if ("".equals(word)) {
            return "";
        }

        if (regex == null) {
            throw new UtilityException("Parameter 'regex' can not be null.");
        }

        if (replacement == null) {
            throw new UtilityException("Parameter 'replacement' can not be null.");
        }

        Pattern pattern = null;
        if (caseSensitive) {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regex);
        }

        return pattern.matcher(word).replaceAll(replacement);
    }

    /**
     * 문자열을 지정된 갯수를 원하는 문자열로 변환한다.
     * 예) replaceString("supermam", -3, "*") -> super***
     * replaceString("supermam", 3, "*") -> ***erman
     * 실제 문자열 길이를 초과하면 모든 문자를 치환한다.
     * 
     * @param word
     * @param length
     *            치환할 문자갯수(양수이면 앞에서 부터, 음수이면 뒤에서부터 치환함)
     * @param replacement
     *            치환할 문자열
     * @return string
     */
    public String replaceString(String word, int length, String replacement) {

        String regexp = null;
        int real_length = (Math.abs(length) > word.length() ? word.length() : Math.abs(length));
        if (length >= 0) {
            regexp = "^.{" + real_length + "}";
        } else {
            regexp = ".{" + real_length + "}$";
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < real_length; i++) {
            buf.append(replacement);
        }
        Pattern pattern = Pattern.compile(regexp);

        return pattern.matcher(word).replaceAll(buf.toString());
    }

    public String convertToPrintableHtml(String str) {
        if (str == null || str.equals("")) {
            return "";
        }

        String ret = str;

        ret = replaceString(ret, "<", "&lt;");
        ret = replaceString(ret, ">", "&gt;");
        ret = replaceString(ret, "\t", "&nbsp;&nbsp;&nbsp;");
        ret = replaceString(ret, "\"", "&#34;");

        ret = convertToEnterKey(ret);
        ret = ret.trim();
        return ret;
    }

    public String convertToEnterKey(String str) {
        if (str == null || str.equals("")) {
            return "";
        }

        String ret = str;

        ret = replaceString(ret, "\n", "<br>");
        ret = replaceString(ret, "\r\n", "<br>");
        return ret;
    }

    public boolean isEmpty(String word) {
        if (word == null) {
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isWhitespace(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 정규식을 이용한 html태그(태그내용) 제거
     */
    public String removeTag(String s) {
        return s.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    /**
     * 작성자:박현진
     * 개요:XSS(크로스 사이트 스크립팅 방지)
     */
    public String xss(String value) {
    	
        if (value == null || value.equals("")) {
            return "";
        }
        String ret = value;

        ret = replaceString(ret, "<", "&lt;");
        ret = replaceString(ret, ">", "&gt;");
        ret = replaceString(ret, "\t", "&nbsp;&nbsp;&nbsp;");

        return ret.trim();
    }
    
    private static final List<String> XSS_TAGS_LIST = Arrays.asList(
    		"alert", "append", "applet", "base", "bgsound", "binding", "blink",
			"charset", "cookie", "create", "document", "embed", "eval", "expression",
			"form", "frame", "iframe", "frameset", "ilayer", "javascript", "layer",
			"link", "meta", "msgbox", "object", "refresh", "script", "string", "vbscript", "void", "xml");

    /**
     * 작성자:박현진
     * 개요:스크립트 태그 무력화, on 이벤트 핸들러 스크립트 무력화
     */
    public String removeScript(String s, String[] excludes_tags) {
		
		Pattern pattern = Pattern.compile("(<\\s*/?\\s*)([a-zA-Z]+)([^<]*)(\\s*>)");	//HTML 태그 찾기
		Matcher matcher = pattern.matcher(s);
		
		List<String> excludes = Optional.ofNullable(excludes_tags).map(Arrays::asList).orElse(new ArrayList<>());
		
		StringBuffer buffer = new StringBuffer();
		
		while (matcher.find()) {
			String tagName = matcher.group(2).toLowerCase();
			String tagContent = matcher.group(3);
			if (!tagContent.trim().isEmpty()) {
				tagContent = matcher.group(3).replaceAll("(?i)(on\\w+)(\\s*=)", "$1-not-allowed$2");	//on 핸들러 변경
			}
			
			tagContent = tagContent.replace("$", "\\$");	//정규식 예약어 escapte 처리
			
			if (!excludes.contains(tagName) && XSS_TAGS_LIST.contains(tagName)) {
				matcher.appendReplacement(buffer, String.format("$1$2-not-allowed%s$4", tagContent));
			} else {
				matcher.appendReplacement(buffer, String.format("$1$2%s$4", tagContent));
			}
		}
		
		matcher.appendTail(buffer);
		
		return buffer.toString();
	}

    public String concat(String... args) {
        if (args == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            if (s == null) {
                continue;
            }
            builder.append(s);
        }
        return builder.toString();
    }
    
    public String nvl(String str, String defaultValue) {

        if (str == null || "".equals(str)) {
            return defaultValue;
        } else {
            return str;
        }

    }

}
