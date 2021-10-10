package com.emfldlem.Common;

import java.text.NumberFormat;

import org.springframework.util.ObjectUtils;

public class commonUtil {

    public static String datetoformat(String iDate, String formate)  {

        String OdateString = "";
        if("yyyy-MM-dd".equals(formate)) {
            OdateString = iDate.substring(0,4) + "-" + iDate.substring(4,6) + "-" + iDate.substring(6,8);
        }

        return OdateString;

    }


    public static String notation(double dbl) {
        return NumberFormat.getInstance().format(dbl);
    }
    public static String notation(int dbl) {
        return NumberFormat.getInstance().format(dbl);
    }
    public static String notation(Object o) {
        if(ObjectUtils.isEmpty(o)) {
            return "";
        } else {
            return NumberFormat.getInstance().format(o);
        }

    }

    public static String nvl(String o) {
        if(ObjectUtils.isEmpty(o)) {
            return "";
        }
        else {
            return o;
        }
    }

    public static Object nvl(Object o) {
        if(ObjectUtils.isEmpty(o)) {
            /*if (o instanceof Integer) {
                return 0;
            } else if (o instanceof Double) {
                return 0;
            }
            else if (o instanceof BigDecimal) {
                return 0;
            }
            else if (o instanceof String) {
                return "";
            }*/
            return "";
        }
        else {
            return o;
        }
    }
}
