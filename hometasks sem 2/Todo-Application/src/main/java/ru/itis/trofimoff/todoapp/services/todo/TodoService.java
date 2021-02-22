package ru.itis.trofimoff.todoapp.services.todo;

import ru.itis.trofimoff.todoapp.models.Todo;

import java.util.List;

public interface TodoService {
    void addUsersTodo(Todo todDto, int userId, String rights);
    void deleteTodo(int todoId, int userId);
    void addTodo(Todo todo);
    void updateTodo(Todo todo);
    List<Todo> getUserTodos(int userId);
    List<Todo> getUserTodosByGroup(int userId, int groupId);
}
