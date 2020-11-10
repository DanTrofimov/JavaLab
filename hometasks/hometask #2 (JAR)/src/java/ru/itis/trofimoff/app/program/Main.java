package ru.itis.trofimoff.app.program.main;

import com.beust.jcommander.JCommander;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.itis.trofimoff.app.utils.Downloader;
import ru.itis.trofimoff.app.utils.FileNamer;
import ru.itis.trofimoff.app.utils.ThreadPool;
import ru.itis.trofimoff.app.utils.Args;

public class Main {
    public static void main(String... argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String[] urls = args.getUrls().split(";");
        ThreadPool threadPool = new ThreadPool(args.getCount());
        Downloader downloader = new Downloader();
        for (int i = 0; i < urls.length; i++){
            int finalI = i;
            threadPool.submit(() -> {
                URL url;
                try {
                    url = new URL(urls[finalI].trim());
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException();
                }
                String fileName = FileNamer.makeNameFileForFileFromURL(url);
                Path path = Paths.get(args.getPath(), fileName);
                System.out.println("Starting download " + fileName);
                downloader.prepareAndDownload(url, path);
                System.out.println("End download " + fileName);
            });
        }
    }
}