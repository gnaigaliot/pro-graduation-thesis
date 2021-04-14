package com.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeHelper {
    Date date;
    public DatetimeHelper() {
        date = new Date();
    }

    public String currentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


}
