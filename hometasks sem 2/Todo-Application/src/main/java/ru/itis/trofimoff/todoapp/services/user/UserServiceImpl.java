package ru.itis.trofimoff.todoapp.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.UserRepository;
import ru.itis.trofimoff.todoapp.utils.EmailUtil;
import ru.itis.trofimoff.todoapp.utils.MailsGenerator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), "Регистрация", from, confirmMail);
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
}
