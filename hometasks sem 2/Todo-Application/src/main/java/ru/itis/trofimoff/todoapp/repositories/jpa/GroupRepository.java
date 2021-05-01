package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.todoapp.models.Group;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findById(int id);
    Optional<Group> findByName(String name);
}
