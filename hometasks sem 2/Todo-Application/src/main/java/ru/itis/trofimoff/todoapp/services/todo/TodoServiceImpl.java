package ru.itis.trofimoff.todoapp.services.todo;

import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.TodoRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void addUsersTodo(TodoDto todoDto, int userId, String rights) {
        Todo todo = new Todo(todoDto);
        if (todo.getText().trim().equals("")) return;
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
    public void addTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);
        if (!todo.getText().trim().equals("")) {
            todoRepository.saveTodo(todo);
        }
        todoDto.setId(todo.getId());
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
    public void updateTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);
        todoRepository.update(todo);
    }
}
