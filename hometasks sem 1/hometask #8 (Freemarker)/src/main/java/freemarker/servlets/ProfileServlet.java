package freemarker.servlets;

import freemarker.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> recentUsers = new ArrayList<User>();

        recentUsers.add(User.builder().id(1L).name("Nikita Dubko").age(19).build());
        recentUsers.add(User.builder().id(2L).name("Vadim Makeev").age(20).build());
        recentUsers.add(User.builder().id(3L).name("Vladimir Grinenko").age(21).build());

        request.getSession().setAttribute("recentUsers", recentUsers);
        getServletContext().getRequestDispatcher("/WEB-INF/views/profile.ftlh").forward(request, response);
    }
}
