package ru.itis.trofimoff.todoapp.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import ru.itis.trofimoff.todoapp.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptor implements HandlerInterceptor {
    private final String[] protectedPaths = {"/main", "/admin", "/admin-add"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
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
    }
}
