package ru.itis.trofimoff.todoapp.repositories.todo;

import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo> {
    void bindUserWithTodo(int id, int userId);

    void saveUserTodo(Todo todo, int userId);

    void deleteByIdAndChangeUserStat(int todoId, int userId);

    void saveTodo(Todo todo);

    List<Todo> getUserTodos(int userId);

    public List<Todo> getUserTodos(int userId, int todoGroup);
}
