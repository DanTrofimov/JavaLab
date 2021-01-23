package ru.itis.trofimoff.crf.repositories;

import ru.itis.trofimoff.crf.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findOneByEmail(String email);
}
