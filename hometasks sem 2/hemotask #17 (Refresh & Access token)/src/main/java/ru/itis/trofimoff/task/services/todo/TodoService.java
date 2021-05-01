package ru.itis.trofimoff.task.services.todo;

import ru.itis.trofimoff.task.models.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAllTodos();
    Todo findTodoById(int id);
    Todo saveTodo(Todo todo);
    void updateTodo(Todo todo);
    Todo deleteTodo(int id);
}
