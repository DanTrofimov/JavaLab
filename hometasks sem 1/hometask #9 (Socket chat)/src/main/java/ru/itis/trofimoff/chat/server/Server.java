package ru.itis.trofimoff.chat.server;

import ru.itis.trofimoff.chat.exceptions.ServerException;
import ru.itis.trofimoff.chat.protocol.Message;
import ru.itis.trofimoff.chat.protocol.Protocol;
import ru.itis.trofimoff.chat.server.connections.Connection;
import ru.itis.trofimoff.chat.server.connections.ConnectionImpl;
import ru.itis.trofimoff.chat.server.connections.ConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;

public class Server implements ConnectionListener {
    private Set<Connection> connections;
    private ServerSocket serverSocket;

    public Server(int port) throws ServerException {
        try {
            connections = new LinkedHashSet();
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            throw new ServerException("problems with server connection", ex);
        }
    }

    public void startServer() throws ServerException {
        System.out.println("server started");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                connectionCreated(new ConnectionImpl(socket, this));
            } catch (IOException ex) {
                throw new ServerException("problems with server connection", ex);
            }
        }
    }

    @Override
    public synchronized void connectionCreated(Connection conn) {
        connections.add(conn);
        System.out.println("connection added");
    }

    @Override
    public synchronized void coonnectionClosed(Connection conn) {
        connections.remove(conn);
        conn.close();
        System.out.println("connection closed");
    }

    @Override
    public synchronized void connectionException(Connection conn, Exception ex) {
        coonnectionClosed(conn);
        ex.printStackTrace();
    }

    @Override
    public synchronized void receivedContent(Message msg) {
        for (Connection conn : connections) {
            if (conn.isRunning()) conn.send(msg);
        }
    }
}
