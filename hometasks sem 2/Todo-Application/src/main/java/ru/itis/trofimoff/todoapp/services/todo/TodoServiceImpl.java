package ru.itis.trofimoff.todoapp.services.todo;

import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.TodoRepository;

import java.util.List;

public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addUsersTodo(Todo todo, int userId, String rights) {
        System.out.println(todo);
        switch (rights) {
            case "admin":
                todo.setGroupId(2);
                todoRepository.bindUserWithTodo(todo.getId(), userId);
                break;
            case "users" :
                todo.setGroupId(1);
                todoRepository.saveUserTodo(todo, userId);
                break;
            default:
        }
    }

    @Override
    public void deleteTodo(int todoId, int userId) {
        todoRepository.deleteById(todoId, userId);
    }

    @Override
    public void addTodo(Todo todo) {
        todoRepository.saveTodo(todo);
    }

    @Override
    public List<Todo> getUserTodos(int userId) {
        return todoRepository.getUserTodos(userId);
    }

    @Override
    public List<Todo> getUserTodosByGroup(int userId, int groupId) {
        return todoRepository.getUserTodos(userId, groupId);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.update(todo);
    }
}
