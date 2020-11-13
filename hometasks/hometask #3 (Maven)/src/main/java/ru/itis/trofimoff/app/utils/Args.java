package ru.itis.trofimoff.app.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.net.URL;
import java.util.List;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = "--mode", description = "Use one or more threads")
    private String mode = "one-thread";

    @Parameter(names = "--count", description = "How many threads will be start")
    private int count = 1;

    @Parameter(names = "--files", description = "Link for download")
    private String urls;

    @Parameter(names = "--folder", description = "Path on PC")
    private String path;

    public String getMode() {
        return mode;
    }

    public int getCount() {
        return count;
    }

    public String getUrls() {
        return urls;
    }

    public String getPath() {
        return path;
    }
}

//--mode=multi-thread --count=10 --files=https://c.files.bbci.co.uk/12A9B/production/_111434467_gettyimages-1143489763.jpg;https://i.ytimg.com/vi/1Ne1hqOXKKI/maxresdefault.jpg --folder=D:/images