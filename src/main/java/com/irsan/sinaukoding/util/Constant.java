package com.irsan.sinaukoding.util;

public class Constant {

    public static final String HEADER_DATA = "User-Data";
    public static final String ROLE_DEFAULT = "Anggota";
    public static final String TIMEZONE_INDONESIA = "Asia/Jakarta";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String BASE_PATH = "/api/v1";
    public static final String[] AUTH_WHITELIST = {
            "/api/v1/signup",
            "/api/v1/signin",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/swagger-ui/**",
    };
    public static final int LONG_DAYS = 7;
    public static final double DENDA = 1500;
    public static final int BATAS_PEMINJAMAN = 3;
    public static final int BATAS_BUKU = 1;
    public static final String STATUS_PINJAM = "PINJAM";
    public static final String STATUS_SELESAI = "SELESAI";
}
