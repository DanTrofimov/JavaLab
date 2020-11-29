package loggs.repository;

import javax.servlet.http.Cookie;

public interface CookieRepository {
    void create(Cookie cookie);
    boolean find(Cookie cookie);
}
