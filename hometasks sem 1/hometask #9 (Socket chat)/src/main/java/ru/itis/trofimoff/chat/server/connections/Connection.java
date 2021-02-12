package ru.itis.trofimoff.chat.server.connections;

import ru.itis.trofimoff.chat.protocol.Message;

public interface Connection {

    public void send(Message msg);

    public boolean isRunning();

    public void close();
}
