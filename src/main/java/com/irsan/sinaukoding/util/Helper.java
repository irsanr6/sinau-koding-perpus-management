package com.irsan.sinaukoding.util;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static Date currentDate() {
//        Reformat date to string
//        Date date = new Date().toString();
//        Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(dateString);
//        DateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//        target.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Jakarta")));
//        log.info("currentDate {}", target.format(date));
        return new Date();
    }

    public static Date dynamicDate(Date date, int num) {
        return DateUtils.addDays(date, num);
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

    public static boolean isNullOrEmpty( final Collection< ? > c ) {
        return c == null || c.isEmpty();
    }

    public static String arrayToString(List<?> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
    }

}
