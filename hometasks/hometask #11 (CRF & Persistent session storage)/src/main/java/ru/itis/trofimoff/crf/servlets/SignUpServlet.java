package ru.itis.trofimoff.crf.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.trofimoff.crf.models.User;
import ru.itis.trofimoff.crf.services.SignUpService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        service = springContext.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Thread.currentThread().getName());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("html/signUp.html");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = User.builder()
                .email(email)
                .password(password)
                .build();

        service.signUp(user);
    }
}
