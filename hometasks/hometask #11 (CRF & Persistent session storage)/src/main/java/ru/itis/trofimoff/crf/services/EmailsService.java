package ru.itis.trofimoff.crf.services;

public interface EmailsService {
    void sendMail(String subject, String text, String email);
}
