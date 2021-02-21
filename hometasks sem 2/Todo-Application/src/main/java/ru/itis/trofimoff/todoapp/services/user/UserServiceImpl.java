package ru.itis.trofimoff.todoapp.services.user;

import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    public Optional<User> findByEmail(SignInFormDto userForm) {
        return this.userRepository.findByEmail(userForm.getEmail());
    }

    public UserStatisticsDto getUserStatistic(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> new UserStatisticsDto(value.getAllTodos(), value.getDoneTodos())).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEMail(String name) {return Optional.empty();}

    @Override
    public void updateUser(User user, HttpServletRequest request) {}

    @Override
    public void deleteUser(User user, HttpServletRequest request) {}
}
