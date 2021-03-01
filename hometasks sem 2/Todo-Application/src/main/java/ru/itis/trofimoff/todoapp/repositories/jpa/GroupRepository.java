package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.todoapp.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {}
