package ru.itis.trofimoff.crf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/image")
public class ImagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("fileUploaded")) {
                File file = new File("C://files/" + cookie.getValue());
                response.setContentType("image/jpeg");
                response.setContentLength((int)file.length());
                response.setHeader("Content-Disposition", "filename=\"" + cookie.getValue() + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
                response.flushBuffer();
            }
        }
    }
}
