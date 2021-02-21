package ru.itis.trofimoff.todoapp.services.user;

import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {
    //    Long saveUser(User user);
    void saveUser(SignUpFormDto user);
    Optional<User> checkUser(SignInFormDto user);
    Optional<User> findUserByEMail(String name);
    UserStatisticsDto getUserStatistic(int userId);
    List<User> findAll();
    void updateUser (User user, HttpServletRequest request);
    void deleteUser (User user, HttpServletRequest request);
}
