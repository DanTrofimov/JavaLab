package ru.itis.trofimoff.task.services.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.task.models.Token;
import ru.itis.trofimoff.task.repository.TokenRepository;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
