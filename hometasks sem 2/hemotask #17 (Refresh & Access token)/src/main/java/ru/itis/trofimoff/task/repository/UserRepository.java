package ru.itis.trofimoff.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.task.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
