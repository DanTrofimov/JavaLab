package ru.itis.trofimoff.crf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        Cookie[] cookies = request.getCookies();
        String fileName = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("fileUploaded")) {
                fileName = cookie.getValue();
            }
        }

        writer.println("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Hello, User</h1>" +
                "<h1>" + fileName + "</h1>\n" +
                "<img src=\"/image\">\n" +
                "</body>\n" +
                "</html>");
    }
}
