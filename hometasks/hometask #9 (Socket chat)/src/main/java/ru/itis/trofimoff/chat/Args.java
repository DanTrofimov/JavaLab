package ru.itis.trofimoff.chat;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "-port", description = "Connection port")
    private int port;

    @Parameter(names = "-server-ip", description = "Connection host")
    private String host;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
