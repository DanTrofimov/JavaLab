package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.trofimoff.todoapp.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // находит по mail
    Optional<User> findByEmail(String email);

    @Query(value = "select * from users where role = 'user'", nativeQuery = true)
    List<User> findAllDefaultUsers();

    // по коду сетает confirmed на true
    @Query("UPDATE User user SET user.confirmed = true WHERE user.confirmCode = :code")
    void confirmUser(@Param("code") String code);
}
