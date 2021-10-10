package com.emfldlem.Common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtility {
    public DateUtility() {
    }

    public String today(String pattern) {
        return this.format(new Date(), pattern);
    }

    public String format(Date date, String pattern) {
        String str = "";
        if (date.toString().substring(0, 19).equals("0001/01/01 00:00:00")) {
            str = "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            str = simpleDateFormat.format(date);
        }

        return str;
    }

    public String format(int amount, String pattern) {
        return this.format(amount, pattern, 5);
    }

    public String format(int amount, String pattern, int gubun) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(gubun, amount);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(calendar.getTime());
    }

    public String format(int amount, String pattern, String datestr) {
        return this.format(amount, pattern, datestr, 5);
    }

    public String format(int amount, String pattern, String datestr, int gubun) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.parse(datestr, pattern));
        calendar.add(gubun, amount);
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(calendar.getTime());
    }



    public Date parse(String datestr, String pattern) {
        try {
            if (datestr == null) {
                throw new Exception();
            }
            else if (pattern == null) {
                throw new Exception();
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
                Date date = null;

                date = formatter.parse(datestr);

                if (!this.format(date, pattern).equals(datestr)) {
                    throw new Exception();
                } else {
                    return date;
                }
            }
        } catch (Exception e) {
            Date date = null;
            return date;
        }

    }


}
