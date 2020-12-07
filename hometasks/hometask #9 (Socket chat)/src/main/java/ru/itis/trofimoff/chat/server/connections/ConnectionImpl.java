package ru.itis.trofimoff.chat.server.connections;

import lombok.SneakyThrows;
import ru.itis.trofimoff.chat.protocol.Message;

import java.io.*;
import java.net.Socket;

public class ConnectionImpl implements Connection, Runnable {
    private Socket socket;
    private ConnectionListener connectionListener;
    private OutputStream out;
    private InputStream in;

    public ConnectionImpl(Socket socket, ConnectionListener connectionListener) {
        try {
            this.socket = socket;
            this.connectionListener = connectionListener;
            out = this.socket.getOutputStream();
            in = socket.getInputStream();
            Thread thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    @Override
    public void send(Message msg) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return socket.isConnected();
    }

    @Override
    public void close() {}

    @SneakyThrows
    @Override
    public void run() {
        while (isRunning()) {
           int amount = in.available();
           if (amount != 0) {
               ObjectInputStream objIn = new ObjectInputStream(in);
               Message msg = (Message) objIn.readObject();
               connectionListener.receivedContent(msg);
           } else {
               Thread.sleep(200);
           }
        }
    }
}
