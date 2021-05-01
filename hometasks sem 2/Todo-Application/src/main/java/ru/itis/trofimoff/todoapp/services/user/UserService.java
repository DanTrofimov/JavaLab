package ru.itis.trofimoff.todoapp.services.user;

import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(SignUpFormDto signUpFormDto);
    Optional<UserDto> findByEmail(String email);
    UserStatisticsDto getUserStatistic(int userId);
    List<UserDto> findAll();
    List<UserDto> findAllDefaultUsers();
    boolean equalsRowPasswordWithHashPassword(String rowPassword, String hashPassword);
    void confirmUser(String code);
}
