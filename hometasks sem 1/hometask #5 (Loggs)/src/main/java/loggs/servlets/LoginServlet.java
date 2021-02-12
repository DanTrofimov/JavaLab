package loggs.servlets;

import loggs.services.CookieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieServiceImpl us = (CookieServiceImpl) getServletContext().getAttribute("authService");

        UUID cookieValue = UUID.randomUUID(); // here u can add ur own cookie value

        // added cookie to the user
        Cookie cookie = new Cookie("auth", "9ec79188-91d3-49a3-84b5-a1cb910d0062");
//        Cookie cookie = new Cookie("auth", "test");
        cookie.setMaxAge(60 * 60 * 24 * 30); // 1 month
        response.addCookie(cookie);

        response.sendRedirect(getServletContext().getContextPath() + "/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
