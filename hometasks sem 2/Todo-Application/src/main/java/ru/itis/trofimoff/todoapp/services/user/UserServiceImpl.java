package ru.itis.trofimoff.todoapp.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.user.UserRepository;
import ru.itis.trofimoff.todoapp.repositories.user.UserRepositoryImpl;
import ru.itis.trofimoff.todoapp.utils.mail.sender.EmailUtil;
import ru.itis.trofimoff.todoapp.utils.mail.generator.MailsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile(value = "master")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value(value = "${server.url}")
    private String serverUrl;

    @Value(value = "${spring.mail.username}")
    private String from;

    @Value(value = "${server.context.application}")
    private String springContextValue;

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(SignUpFormDto signUpFormDto) {
        User user = new User(signUpFormDto);
        user.setConfirmCode(UUID.randomUUID().toString());
        String hashPassword = passwordEncoder.encode(signUpFormDto.getPassword());
        user.setPassword(hashPassword);
        this.userRepository.save(user);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode(), springContextValue);
        emailUtil.sendMail(user.getEmail(), "Registration", from, confirmMail);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            return Optional.of(new UserDto(user.get()));
        } else {
           return Optional.empty();
        }
    }

    @Override
    public boolean equalsRowPasswordWithHashPassword(String rowPassword, String hashPassword) {
        return passwordEncoder.matches(rowPassword, hashPassword);
    }

    public UserStatisticsDto getUserStatistic(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> new UserStatisticsDto(value.getAllTodos(), value.getDoneTodos())).orElse(null);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            userDtos.add(new UserDto(user));
        });
        return userDtos;
    }

    @Override
    public void confirmUser(String code) {
        userRepository.confirmUser(code);
    }
}
