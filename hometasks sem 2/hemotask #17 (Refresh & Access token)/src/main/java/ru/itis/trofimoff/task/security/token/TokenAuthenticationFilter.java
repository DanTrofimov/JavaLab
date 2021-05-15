package ru.itis.trofimoff.task.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.trofimoff.task.services.jwt.JwtBlacklistService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtBlacklistService service;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, IOException, ServletException {
        String token = request.getHeader("X-TOKEN");


        if (token != null) {

            if (service.exists(token)) {
                System.out.println("forbidden!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}
