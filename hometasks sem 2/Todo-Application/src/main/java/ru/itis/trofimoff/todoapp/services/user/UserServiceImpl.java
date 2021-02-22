package ru.itis.trofimoff.todoapp.services.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(SignUpFormDto signUpFormDto) {
        User user = new User(signUpFormDto);
        String hashPassword = passwordEncoder.encode(signUpFormDto.getPassword());
        user.setPassword(hashPassword);
        this.userRepository.save(user);
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
