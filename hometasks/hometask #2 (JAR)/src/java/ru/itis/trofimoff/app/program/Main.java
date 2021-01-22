package ru.itis.trofimoff.app.main;

import com.beust.jcommander.JCommander;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.itis.trofimoff.app.utils.FileDownloader;
import ru.itis.trofimoff.app.utils.Namer;
import ru.itis.trofimoff.app.utils.ThreadPool;
import ru.itis.trofimoff.app.utils.Args;

public class Main {
    public static void main(String... argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        // закинули все урлы в массив
        String[] urls = args.getUrls().split(";");

        // передали количество потоков
        ThreadPool threadPool = new ThreadPool(args.getCount());
        FileDownloader fileDownloader = new FileDownloader();

        for (int i = 0; i < urls.length; i++){
            int index = i;
            threadPool.submit(() -> {
                URL url;
                try {
                    url = new URL(urls[index].trim());
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException();
                }

                String fileName = Namer.createFileName(url);
                Path path = Paths.get(args.getPath(), fileName);
                fileDownloader.prepare(url, path);
                fileDownloader.download();
            });
        }
    }
}