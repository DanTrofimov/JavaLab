package ru.itis.trofimoff.chat.protocol;

public class MessageImpl implements Message {
    private String nick = null;
    private String content;
    private int type = 0;

    public MessageImpl(String nick, String content, int type) {
        this.nick = nick;
        this.content = content;
        this.type = type;
    }

    public MessageImpl(String content) {
        this.content = content;
    }

    public MessageImpl(String content, String nick) {
        this.content = content;
        this.nick = nick;
    }

    @Override
    public String gitNick() {
        return nick;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return nick + ": " + content;
    }
}
