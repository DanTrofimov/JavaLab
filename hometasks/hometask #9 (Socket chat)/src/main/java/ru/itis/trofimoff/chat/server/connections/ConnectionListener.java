package ru.itis.trofimoff.chat.server.connections;

import ru.itis.trofimoff.chat.protocol.Message;

public interface ConnectionListener {
    public void connectionCreated(Connection conn);

    public void coonnectionClosed(Connection conn);

    public void connectionException(Connection conn, Exception ex);

    public void receivedContent(Message msg);
}
