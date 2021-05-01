package ru.itis.trofimoff.todoapp.services.todo;

import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.models.Todo;

import java.util.List;

public interface TodoService {
    void addUsersTodo(TodoDto todoDto, int userId, String rights);
    void deleteTodo(int todoId, int userId);
    void addTodo(TodoDto todo, Group group);
    void updateTodo(TodoDto todo);
    List<Todo> getUserTodos(int userId);
    List<Todo> getUserTodosWithPagination(int userId, int page, int size);
    List<Todo> getUserTodosByGroup(int userId, int groupId);
    int getUsersTodosAmount(int userId);
}
