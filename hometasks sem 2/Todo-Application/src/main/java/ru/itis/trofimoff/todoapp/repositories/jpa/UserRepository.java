package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.trofimoff.todoapp.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // находит по mail
//    @Query("select user from User user where user.email = :email")
//    Optional<User> findByEmail(@Param("email") String email);
//
    Optional<User> findByEmail(String email); // null



//    // по коду сетает confirmed на true
//    void confirmUser(String code);

}
