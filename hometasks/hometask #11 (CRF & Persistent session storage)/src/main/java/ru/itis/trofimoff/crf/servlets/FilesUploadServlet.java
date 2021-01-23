package ru.itis.trofimoff.crf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/files")
@MultipartConfig
public class FilesUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("html/fileUpload.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        System.out.print(part.getSubmittedFileName() + " ");
        System.out.print(part.getContentType() + " ");
        System.out.println(part.getSize());

        Files.copy(part.getInputStream(), Paths.get("C://files/" + part.getSubmittedFileName()));
        Cookie cookie = new Cookie("fileUploaded", part.getSubmittedFileName());
        response.addCookie(cookie);
        response.sendRedirect("/files");
    }
}
