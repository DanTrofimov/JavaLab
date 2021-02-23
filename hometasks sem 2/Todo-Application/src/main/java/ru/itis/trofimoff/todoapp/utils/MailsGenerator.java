package ru.itis.trofimoff.todoapp.utils;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code);
}
