package com.irsan.sinaukoding.util;

import com.google.common.base.Strings;
import com.irsan.sinaukoding.model.UserData;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class SessionUtil {

    @Autowired
    private static Parser parser;

    @Autowired
    private static DatabaseReader databaseReader;

    private static final String UNKNOWN = "UNKNOWN";

    public static UserData getUserData(HttpServletRequest request) {
        return (UserData) request.getAttribute(Constant.HEADER_DATA);
    }

    public static String getUserAgent(HttpServletRequest request) {
        return (String) request.getHeader(Constant.USER_AGENT);
    }

    public static String getDeviceDetails(String userAgent) {
        String deviceDetails = UNKNOWN;

        Client client = parser.parse(userAgent);
        if (Objects.nonNull(client)) {
            deviceDetails = client.userAgent.family
                    + " " + client.userAgent.major + "."
                    + client.userAgent.minor + " - "
                    + client.os.family + " " + client.os.major
                    + "." + client.os.minor;
        }
        return deviceDetails;
    }

    public static String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    public static String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request
                .getHeader("x-forwarded-for");
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    public static String getIpLocation(String ip) throws IOException, GeoIp2Exception {

        String location = UNKNOWN;

        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (Objects.nonNull(cityResponse) &&
                Objects.nonNull(cityResponse.getCity()) &&
                !Strings.isNullOrEmpty(cityResponse.getCity().getName())) {

            location = cityResponse.getCity().getName();
        }

        return location;
    }


}
