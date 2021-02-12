package ru.itis.trofimoff.chat.protocol;

import java.io.Serializable;

public interface Message extends Serializable {
    public String gitNick();
    public int getType();
    public String getContent();
}
