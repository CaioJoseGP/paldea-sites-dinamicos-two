package com.jardim.paldea.support;

import jakarta.servlet.http.HttpSession;

public class SessionHelper {

    private static final String USER_ATTRIBUTE = "usuario";

    public static boolean hasUser(HttpSession session) {
        return session.getAttribute(USER_ATTRIBUTE) != null;
    }

    public static void login(HttpSession session, String displayName) {
        session.setAttribute(USER_ATTRIBUTE, displayName);
    }
}
