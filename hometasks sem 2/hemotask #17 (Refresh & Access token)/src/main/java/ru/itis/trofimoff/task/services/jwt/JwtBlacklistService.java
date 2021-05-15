package ru.itis.trofimoff.task.services.jwt;

public interface JwtBlacklistService {
    void add(String token);

    boolean exists(String token);
}