package ru.itis.trofimoff.crf.services;

public interface SignInService {
    boolean authenticate(String email, String password);
}
