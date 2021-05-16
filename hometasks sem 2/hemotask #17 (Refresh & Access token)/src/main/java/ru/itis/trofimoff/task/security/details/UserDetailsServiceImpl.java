package ru.itis.trofimoff.task.security.details;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.task.models.Token;
import ru.itis.trofimoff.task.models.User;
import ru.itis.trofimoff.task.repository.TokenRepository;
import ru.itis.trofimoff.task.repository.UserRepository;
import ru.itis.trofimoff.task.services.user.UserService;
import ru.itis.trofimoff.task.utils.TokenGenerator;

import java.util.Optional;
import java.util.function.Supplier;

@Component("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @SneakyThrows

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        // getting access token -> building user -> creating UserDetails

        String email = tokenGenerator.verifyToken(token).getEmail();
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
