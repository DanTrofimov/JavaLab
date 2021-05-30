package ru.itis.trofimoff.task.services.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.task.models.Todo;
import ru.itis.trofimoff.task.repository.TodoRepository;
import ru.itis.trofimoff.task.repository.jdbc.TodoJdbcRepository;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    public TodoRepository todoRepository;

    @Autowired
    public TodoJdbcRepository todoJdbcRepository;

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
    public Todo updateTodo(Todo todo) {
        todoRepository.update(todo.getText(), todo.getId());
        return todoRepository.findById(todo.getId());
    }

    @Override
    public Todo deleteTodo(int id) {
        Todo deletingTodo = todoRepository.findById(id);
        todoRepository.deleteById(id);
        return deletingTodo;
    }

    @Override
    public List<Todo> findAllJdbcTodos() {
        return todoJdbcRepository.findAllTodos();
    }
}
