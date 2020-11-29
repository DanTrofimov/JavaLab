package loggs.services;

import loggs.repository.CookieRepositoryImpl;

import javax.servlet.http.Cookie;

public class CookieServiceImpl {
    private final CookieRepositoryImpl userRepository;

    public CookieServiceImpl(CookieRepositoryImpl authRepository) {
        this.userRepository = authRepository;
    }

    public void addCookieAuth(Cookie cookie) {
        userRepository.create(cookie);
    }

    public boolean findCookieAuth(Cookie cookie) {
        return userRepository.find(cookie);
    }
}

