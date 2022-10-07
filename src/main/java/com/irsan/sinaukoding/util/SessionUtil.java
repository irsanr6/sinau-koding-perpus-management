package com.irsan.sinaukoding.util;

import com.irsan.sinaukoding.model.UserData;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    public static UserData getUserData(HttpServletRequest request) {
        return (UserData) request.getAttribute(Constant.HEADER_DATA);
    }

}
