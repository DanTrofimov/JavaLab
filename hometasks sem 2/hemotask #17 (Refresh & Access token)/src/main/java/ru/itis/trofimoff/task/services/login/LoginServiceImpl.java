package ru.itis.trofimoff.task.services.login;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.task.dto.EmailPasswordDto;
import ru.itis.trofimoff.task.dto.TokensDto;
import ru.itis.trofimoff.task.models.Token;
import ru.itis.trofimoff.task.models.User;
import ru.itis.trofimoff.task.repository.TokenRepository;
import ru.itis.trofimoff.task.services.user.UserService;
import ru.itis.trofimoff.task.utils.TokenGenerator;

import java.util.Optional;
import java.util.UUID;

import java.util.function.Supplier;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRepository tokensRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @SneakyThrows
    @Override
    public TokensDto login(EmailPasswordDto emailPassword) {
        User user = userService.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {

            // created refresh token for user
            String tokenValue = UUID.randomUUID().toString();
            Token refreshToken = Token.builder()
                    .token(tokenValue)
                    .user(user)
                    .build();

            tokensRepository.save(refreshToken);

            return TokensDto.builder()
                    .accessToken(tokenGenerator.createAccessToken(user))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public TokensDto refresh(String refreshToken) {
        Optional<Token> token = tokensRepository.findByToken(refreshToken);
        if (token.isPresent()) {
            // created refresh token for user
            String tokenValue = UUID.randomUUID().toString();
            Token newRefreshToken = Token.builder()
                    .token(tokenValue)
                    .user(token.get().getUser())
                    .build();

            tokensRepository.save(newRefreshToken);

            return TokensDto.builder()
                    .accessToken(tokenGenerator.createAccessToken(token.get().getUser()))
                    .refreshToken(tokenValue)
                    .build();
        } else {
            throw new UsernameNotFoundException("Unknown user");
        }
    }
}
