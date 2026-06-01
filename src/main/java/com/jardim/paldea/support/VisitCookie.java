package com.jardim.paldea.support;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VisitCookie {

    private static final String COOKIE_NAME = "totalVisitas";

    // Entrega 3 - cookie simples
    public static void increment(HttpServletRequest request, HttpServletResponse response) {
        int totalVisits = current(request) + 1;
        Cookie cookie = new Cookie(COOKIE_NAME, String.valueOf(totalVisits));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }

    public static int current(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return 0;
        }

        for (Cookie cookie : cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return parseTotal(cookie.getValue());
            }
        }

        return 0;
    }

    private static int parseTotal(String value) {
        try {
            int total = Integer.parseInt(value);
            return Math.max(total, 0);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }
}
