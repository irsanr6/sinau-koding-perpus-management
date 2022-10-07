package com.irsan.sinaukoding.util;

import java.util.Date;

public class Helper {

    public static Date currentDate() {
        Date date = new Date();
//        Reformat date to string
//        Date date = new Date().toString();
//        Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dateString);
//        DateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//        target.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Jakarta")));
//        log.info("currentDate {}", target.format(date));
        return date;
    }

}
