package main.java.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CookieUtil {
    private static final String sessionCookie = "profile";

    public static UUID getSessionCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(sessionCookie)) {
                    return UUID.fromString(cookie.getValue());
                }
            }
        }
        return null;
    }

    public static void setSessionCookie(HttpServletResponse res, UUID uuid) {
        Cookie c = new Cookie(sessionCookie, uuid.toString());
        c.setMaxAge(30 * 60);
        res.addCookie(c);
    }

    public static void removeSessionCookie(HttpServletResponse res) {
        Cookie c = new Cookie(sessionCookie, "");
        c.setMaxAge(0);
        res.addCookie(c);
    }
}
