package ru.itis.trofimoff.todoapp.utils.mail.generator;

public interface MailsGenerator {
    String getMailForConfirm(String serverUrl, String code, String contextApp);
}
