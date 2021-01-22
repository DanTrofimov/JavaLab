package ru.itis.trofimoff.app.utils;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {

    @Parameter(names = "--mode")
    private String mode = "one-thread";

    public String getMode() {
        return mode;
    }

    @Parameter(names = "--count")
    private int count = 1;

    public int getCount() {
        return count;
    }

    @Parameter(names = "--folder")
    private String path;

    public String getUrls() {
        return urls;
    }

    @Parameter(names = "--files")
    private String urls;

    public String getPath() {
        return path;
    }
}

// --mode=multi-thread --count=10 --files=https://c.files.bbci.co.uk/12A9B/production/_111434467_gettyimages-1143489763.jpg;https://i.ytimg.com/vi/1Ne1hqOXKKI/maxresdefault.jpg --folder=D:/images