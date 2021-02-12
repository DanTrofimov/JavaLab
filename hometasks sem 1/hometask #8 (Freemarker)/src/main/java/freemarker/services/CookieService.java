package freemarker.services;

import javax.servlet.http.Cookie;

public interface CookieService {
    void addCookieAuth(Cookie cookie);
    boolean findCookieAuth(Cookie cookie);
}
