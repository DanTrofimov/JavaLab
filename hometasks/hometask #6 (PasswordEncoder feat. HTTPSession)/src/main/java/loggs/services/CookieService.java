package loggs.services;

import loggs.repository.CookieRepository;

import javax.servlet.http.Cookie;

public class CookieService {
    private final CookieRepository userRepository;

    public CookieService(CookieRepository authRepository) {
        this.userRepository = authRepository;
    }

    public void addCookieAuth(Cookie cookie) {
        userRepository.create(cookie);
    }

    public boolean findCookieAuth(Cookie cookie) {
        return userRepository.find(cookie);
    }
}

