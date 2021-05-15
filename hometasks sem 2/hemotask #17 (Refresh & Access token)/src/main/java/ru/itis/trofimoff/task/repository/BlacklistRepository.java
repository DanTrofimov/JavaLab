package ru.itis.trofimoff.task.repository;

public interface BlacklistRepository {
    void save(String token);

    boolean exists(String token);
}