package ru.itis.trofimoff.crf.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static ru.itis.trofimoff.crf.filters.ResponseUtil.sendForbidden;


public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            String sessionCsrf = (String) request.getSession(false).getAttribute("_csrf_token");
            // if csrf token is already exist
            if (sessionCsrf.contains(requestCsrf)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                sendForbidden(request, response);
                return;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            // if that's first crf token
            if (request.getSession().getAttribute("_csrf_tokens") == null) {
                Set<String> csrfTokens = new HashSet<>();
                csrfTokens.add(csrf);
                request.getSession().setAttribute("_csrf_token", csrfTokens);
            } else {
                Set csrfTokens = (Set) request.getSession().getAttribute("_csrf_tokens");
                csrfTokens.add(csrf);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
