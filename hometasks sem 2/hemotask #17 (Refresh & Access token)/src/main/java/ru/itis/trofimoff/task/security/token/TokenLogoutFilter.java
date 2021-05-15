package ru.itis.trofimoff.task.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.trofimoff.task.services.jwt.JwtBlacklistService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenLogoutFilter extends OncePerRequestFilter {
    private final RequestMatcher logoutRequest = new AntPathRequestMatcher("/logout", "GET");

    @Autowired
    private JwtBlacklistService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (logoutRequest.matches(request)) {
            service.add(request.getHeader("X-TOKEN"));
            SecurityContextHolder.clearContext();
            return;
        }

        filterChain.doFilter(request, httpServletResponse);
    }
}
