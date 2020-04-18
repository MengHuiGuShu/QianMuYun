package ToolsUtils;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeTools {
    public static String getTime() {
        Calendar calendar;
        String time;
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);        //需要在获取时间基础上+1
        String day = String.valueOf(calendar.get(Calendar.DATE));
        String hour = String.valueOf(calendar.get(Calendar.HOUR));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        time = year+"-"+month+"-"+day+"-"+hour+"-"+minute;
        return time;
    }
}
