package ru.itis.trofimoff.crf.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.trofimoff.crf.models.User;
import ru.itis.trofimoff.crf.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private UsersService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        service = springContext.getBean(UsersService.class);
    }

    // GET /users?userId=1
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdAsString = request.getParameter("userId");
        Long userId = Long.parseLong(userIdAsString);

        Optional<User> userOptional = service.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            request.setAttribute("user", user);
            request.getRequestDispatcher("jsp/usersPage.jsp").forward(request, response);
        } else {
            response.setStatus(404);
            request.getRequestDispatcher("html/errorPage.html").forward(request, response);
        }
    }

    // POST /users?action=delete&userId=2
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
            service.deleteUserById(Long.parseLong(request.getParameter("userId")));
        }
        response.sendRedirect("/profile");
    }
}
