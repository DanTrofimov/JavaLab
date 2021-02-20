package ru.itis.trofimoff.todoapp.services.user;

import org.springframework.stereotype.Component;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(SignUpFormDto userForm) {
        this.userRepository.save(new User(userForm));
    }

    @Override
    public User checkUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findUserByEMail(String name) {
        return Optional.empty();
    }

    @Override
    public void updateUser(User user, HttpServletRequest request) {

    }

    @Override
    public void deleteUser(User user, HttpServletRequest request) {

    }
}
