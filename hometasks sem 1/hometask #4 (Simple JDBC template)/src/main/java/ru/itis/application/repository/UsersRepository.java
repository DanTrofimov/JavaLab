package ru.itis.application.repository;

import ru.itis.application.models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findAllByAge(int age);
}
