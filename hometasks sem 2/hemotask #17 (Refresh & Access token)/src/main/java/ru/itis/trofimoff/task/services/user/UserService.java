package ru.itis.trofimoff.task.services.user;

import ru.itis.trofimoff.task.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    void blockUser(Integer userId);
}
