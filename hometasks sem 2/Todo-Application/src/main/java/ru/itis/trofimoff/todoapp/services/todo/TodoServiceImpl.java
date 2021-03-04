package ru.itis.trofimoff.todoapp.services.todo;

import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.repositories.jpa.TodoRepository;

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
                todoRepository.insertTodoIntoUsersTodo(todo.getId(), userId);
                todoRepository.incrementUserStatAll(userId);
                break;
            case "users" :
                todo.setGroupId(1);
                Todo generatedTodo = todoRepository.save(todo); // check how it works
                todoRepository.insertTodoIntoUsersTodo(generatedTodo.getId(), userId);
                todoRepository.incrementUserStatAll(userId);
                break;
            default:
        }
    }

    @Override
    public void deleteTodo(int todoId, int userId) {
        todoRepository.deleteById(todoId);
        todoRepository.incrementUserStatDone(userId);
    }

    @Override
    public void addTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);
        if (!todo.getText().trim().equals("")) {
            todoRepository.save(todo);
        }
        todoDto.setId(todo.getId());
    }


    @Override
    public List<Todo> getUserTodos(int userId) {
        return todoRepository.getUsersTodo(userId);
    }

    @Override
    public List<Todo> getUserTodosByGroup(int userId, int groupId) {
        return todoRepository.getUsersTodoByGroup(userId, groupId);
    }

    @Override
    public void updateTodo(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);
        todoRepository.update(todo.getText(), todo.getId());
    }
}
