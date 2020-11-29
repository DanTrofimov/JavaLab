package loggs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isRegistered = request.getSession().getAttribute("currentUser") != null;
        if (!isRegistered) {
            request.getSession().setAttribute("currentUser", true);
            request.getSession().setAttribute("info", "now u in the system");
            response.sendRedirect(getServletContext().getContextPath() + "/login");
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
