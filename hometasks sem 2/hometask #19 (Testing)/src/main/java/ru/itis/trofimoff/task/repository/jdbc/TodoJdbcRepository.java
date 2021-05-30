package ru.itis.trofimoff.task.repository.jdbc;

import ru.itis.trofimoff.task.models.Todo;

import java.util.List;

public interface TodoJdbcRepository {
    public List<Todo> findAllTodos();
}
