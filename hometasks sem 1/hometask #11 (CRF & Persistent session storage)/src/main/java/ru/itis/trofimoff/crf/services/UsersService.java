package ru.itis.trofimoff.crf.services;

import ru.itis.trofimoff.crf.models.User;

import java.util.Optional;

public interface UsersService {
    Optional<User> getUserById(Long id);

    void deleteUserById(long userId);
}
