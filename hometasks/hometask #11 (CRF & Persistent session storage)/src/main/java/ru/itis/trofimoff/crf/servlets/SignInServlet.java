package ru.itis.trofimoff.crf.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.trofimoff.crf.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.itis.trofimoff.crf.filters.ResponseUtil.sendForbidden;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        service = springContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signInPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // /signIn?redirect=/users?userId=2
        if (service.authenticate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("authenticated", true);
            String redirect = request.getParameter("redirect");

            if (redirect == null) {
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect(redirect);
            }
        } else {
            sendForbidden(request, response);
        }
    }
}
