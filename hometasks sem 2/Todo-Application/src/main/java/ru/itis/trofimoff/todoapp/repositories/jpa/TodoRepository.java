package ru.itis.trofimoff.todoapp.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.trofimoff.todoapp.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {}
