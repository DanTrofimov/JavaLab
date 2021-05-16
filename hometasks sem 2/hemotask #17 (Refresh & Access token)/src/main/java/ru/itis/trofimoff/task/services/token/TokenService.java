package ru.itis.trofimoff.task.services.token;

import ru.itis.trofimoff.task.models.Token;

import java.util.Optional;

public interface TokenService {
    Optional<Token> findByToken(String token);
}
