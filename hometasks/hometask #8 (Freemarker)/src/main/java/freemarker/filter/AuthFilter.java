package freemarker.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/profile")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        boolean isRegistered = req.getSession().getAttribute("currentUser") != null;

        if (isRegistered) {
            chain.doFilter(request, response);
            return;
        }

        resp.sendRedirect(req.getServletContext().getContextPath() + "/login");
    }
}
