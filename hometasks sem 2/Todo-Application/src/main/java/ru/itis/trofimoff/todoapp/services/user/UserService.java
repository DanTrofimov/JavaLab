package ru.itis.trofimoff.todoapp.services.user;

import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(SignUpFormDto user);
    Optional<User> findByEmail(String email);
    UserStatisticsDto getUserStatistic(int userId);
    List<User> findAll();
    boolean equalsRowPasswordWithHashPassword(String rowPassword, String hashPassword);
}
