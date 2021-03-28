package ru.itis.trofimoff.todoapp.interceptors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.trofimoff.todoapp.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor implements HandlerInterceptor {
    private final String[] protectedPaths = {"/main", "/admin", "/admin-add"};

    @Autowired
    private Logger logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean isProtected = false;
        String currentPath = request.getRequestURI();
        currentPath = currentPath.substring(request.getContextPath().length());
        for (String path : protectedPaths){
            if (path.equals(currentPath)) {
                isProtected = true;
                break;
            }
        }

        UserDto user = (UserDto) request.getSession().getAttribute("currentUser");
        try {
            if (user == null) {
                if (isProtected) {
                    response.sendRedirect(request.getContextPath() + "/sign-in");
                    return false;
                } else {
                    return true;
                }
            } else {
                switch (user.getRole()){
                    case "user":
                        if (isProtected && !currentPath.equals("/main")) {
                            response.sendRedirect(request.getContextPath() + "/main");
                            return false;
                        } else {
                            return true;
                        }
                    case "admin":
                        if (isProtected && currentPath.equals("/main")) {
                            response.sendRedirect(request.getContextPath() + "/admin");
                            return false;
                        } else {
                            return true;
                        }
                    default:
                        return true;
                }
            }
        } catch (IOException ex) {
            logger.error("Exception: {}, Message: {}, Stacktrace: {}", ex, ex.getMessage(), ex.getStackTrace());
            return false;
        }
    }
}
