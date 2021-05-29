package ru.itis.trofimoff.task.services.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.task.models.Todo;
import ru.itis.trofimoff.task.repository.TodoRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    public TodoRepository todoRepository;

    @Override
    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findTodoById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.update(todo.getText(), todo.getId());
    }

    @Override
    public Todo deleteTodo(int id) {
        return  todoRepository.deleteById(id);
    }
}
