package ru.itis.trofimoff.chat.client;

import ru.itis.trofimoff.chat.exceptions.ClientException;
import ru.itis.trofimoff.chat.protocol.Message;
import ru.itis.trofimoff.chat.protocol.MessageImpl;
import ru.itis.trofimoff.chat.protocol.Protocol;
import ru.itis.trofimoff.chat.server.connections.Connection;
import ru.itis.trofimoff.chat.server.connections.ConnectionImpl;
import ru.itis.trofimoff.chat.server.connections.ConnectionListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements ConnectionListener {
    private ConnectionImpl conn;
    private String nickName;
    private String host;
    private int port;

    public Client(String nickName, String host, int port) {
        this.nickName = nickName;
        this.host = host;
        this.port = port;
    }

    public void start() throws ClientException {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket(InetAddress.getByName(this.host), this.port);
            conn = new ConnectionImpl(socket, this);
            connectionCreated(conn);
            while (conn.isRunning()) {
                Message msg = new MessageImpl(scanner.nextLine(), this.nickName);
                conn.send(msg);
            }
        } catch (IOException ex) {
            throw new ClientException("problems with client connection", ex);
        }
    }

    public void destroy() {
        coonnectionClosed(conn);
        this.conn.close();
    }

    @Override
    public void connectionCreated(Connection conn) {
        System.out.println("client connection created");
        this.conn = (ConnectionImpl) conn;
    }

    @Override
    public void coonnectionClosed(Connection conn) {
        System.out.println("client connection closed");
    }

    @Override
    public void connectionException(Connection conn, Exception ex) {
        System.out.println("client connection exception: ");
        ex.printStackTrace();
    }

    @Override
    public void receivedContent(Message msg) {
        System.out.println(msg);
    }
}
