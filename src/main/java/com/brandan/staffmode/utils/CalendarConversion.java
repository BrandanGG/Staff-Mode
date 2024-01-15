package com.brandan.staffmode.utils;

import java.util.Calendar;

public class CalendarConversion {
    public static Calendar convertTime(String time) {
        Calendar cal = Calendar.getInstance();
        if (time.contains("s")) {
            int num = Integer.parseInt((time.replace("s", "").trim()));
            cal.add(Calendar.SECOND, num);
        } else if (time.contains("m")) {
            int num = Integer.parseInt((time.replace("m", "").trim()));
            cal.add(Calendar.MINUTE, num);
        } else if (time.contains("h")) {
            int num = Integer.parseInt((time.replace("h", "").trim()));
            cal.add(Calendar.HOUR, num);
        } else if (time.contains("d")) {
            int num = Integer.parseInt((time.replace("d", "").trim()));
            cal.add(Calendar.DAY_OF_MONTH, num);
        }
        else {
            return null;
        }
        return cal;
    }

}
