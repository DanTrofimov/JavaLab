package ru.itis.trofimoff.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.task.models.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
