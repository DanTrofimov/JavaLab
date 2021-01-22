package ru.itis.trofimoff.app.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class Namer {

    public static String createFileName(URL url) {
        String fileName = Paths.get(url.getFile()).getFileName().toString();
        if (fileName.split("[/.]").length > 1) return fileName;
        URLConnection connection;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        String mimeType = connection.getContentType();
        String type = mimeType.split("[/;]")[1];
        return fileName + "." + type;
    }
}