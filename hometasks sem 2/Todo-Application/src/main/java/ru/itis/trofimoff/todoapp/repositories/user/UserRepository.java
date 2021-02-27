package ru.itis.trofimoff.todoapp.repositories.user;

import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(int userId);

    void confirmUser(String code);
}
