package ru.itis.trofimoff.chat;

import com.beust.jcommander.JCommander;
import ru.itis.trofimoff.chat.exceptions.ServerException;
import ru.itis.trofimoff.chat.server.Server;

public class AppServer {
    public static void main(String ... argv) {
        try {
            Args args = new Args();
            JCommander.newBuilder()
                    .addObject(args)
                    .build()
                    .parse(argv);
            Server server = new Server(args.getPort());
            server.startServer();
        } catch (ServerException ex) {
            System.out.println("problems with server connection");
            System.err.println(ex);
        }
    }
}
