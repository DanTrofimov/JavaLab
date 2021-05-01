package ru.itis.trofimoff.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.trofimoff.task.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from account where email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
