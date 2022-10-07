package com.irsan.sinaukoding.util;

public class Constant {

    public static final String HEADER_DATA = "User-Data";
    public static final String ROLE_DEFAULT = "Anggota";
    public static final String TIMEZONE_INDONESIA = "Asia/Jakarta";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String[] AUTH_WHITELIST = {
            "/api/v1/signup",
            "/api/v1/signin",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/swagger-ui/**",
    };

}
