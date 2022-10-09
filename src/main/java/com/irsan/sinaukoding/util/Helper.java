package com.irsan.sinaukoding.util;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    public static Date dynamicDate(Date date, int num) {
        Date dynamic = DateUtils.addDays(date, num);
        return dynamic;
    }

    public static PageRequest getPageRequest(int pageNumber, int pageSize, String sortBy) {
        return PageRequest.of(--pageNumber, pageSize, Sort.by(sortBy).ascending());
    }

    public static double getDenda(Date pengembalianDate, Date peminjamanDate) {
        double denda = Constant.DENDA;
        long diff = (pengembalianDate.getTime() - peminjamanDate.getTime()) / (24 * 60 * 60 * 1000);
        if (diff >= 8) {
            denda = denda * (diff - Constant.LONG_DAYS);
        } else {
            denda = 0;
        }
        return denda;
    }

}
