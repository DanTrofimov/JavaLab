package ru.itis.trofimoff.chat;

import com.beust.jcommander.JCommander;
import ru.itis.trofimoff.chat.client.Client;
import ru.itis.trofimoff.chat.exceptions.ClientException;

public class AppClient {
    private static String[] names = new String[]{"john", "moe", "bob", "kevin", "kenny"};

    public static void main(String ... argv) {
        try {
            Args args = new Args();
            JCommander.newBuilder()
                    .addObject(args)
                    .build()
                    .parse(argv);
            Client client = new Client(names[(int) Math.round(Math.random() * 4)], args.getHost(), args.getPort());
            client.start();
        } catch (ClientException ex) {
            System.out.println("problems with client connection");
            System.err.println(ex);
        }
    }
}

