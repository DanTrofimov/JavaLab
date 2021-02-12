package loggs.filter;

import loggs.services.CookieServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        CookieServiceImpl cs = (CookieServiceImpl) req.getServletContext().getAttribute("authService");

        Cookie[] cookies = ((HttpServletRequest) request).getCookies(); // get all user's cookies

        for (Cookie cookie: cookies) {
            if (cs.findCookieAuth(cookie)) {
                chain.doFilter(request, response);
                return;
            }
        }

        resp.sendRedirect(req.getServletContext().getContextPath() + "/login");

    }
}
