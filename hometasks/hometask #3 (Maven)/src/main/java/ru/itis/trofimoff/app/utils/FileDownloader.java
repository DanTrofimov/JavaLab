package ru.itis.trofimoff.app.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FileDownloader {

    private byte[] byteBuffer;
    private Queue<InputStream> inputStreams;
    private Queue<OutputStream> outputStreams;

    public FileDownloader() {
        byteBuffer = new byte[512];
        inputStreams = new ConcurrentLinkedQueue<>();
        outputStreams = new ConcurrentLinkedQueue<>();
    }

    public void prepare(URL url, Path file) {
        try {
            inputStreams.add(url.openStream());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        try {
            outputStreams.add(new FileOutputStream(file.toAbsolutePath().toString()));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }

        try {
            outputStreams.add(new FileOutputStream(file.toAbsolutePath().toString()));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public String generateName(URL url) {
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

    public void download() {
        int byteRead;
        InputStream inputStream = inputStreams.poll();
        OutputStream outputStream = outputStreams.poll();
        try {
            boolean end = false;
            while (!end) {
                synchronized (byteBuffer) {
                    if ((byteRead = inputStream.read(byteBuffer, 0, 512)) != -1) {
                        outputStream.write(byteBuffer, 0, byteRead);
                    } else {
                        end = true;
                    }
                }
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}