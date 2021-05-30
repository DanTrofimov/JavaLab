package ru.itis.trofimoff.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.trofimoff.task.models.Todo;
import ru.itis.trofimoff.task.services.todo.TodoService;

import java.util.List;

@RestController
public class RestControllerExample {

    @Autowired
    public TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.findAllTodos());
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> postTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.saveTodo(todo));
    }

    @PutMapping("/todos")
    public ResponseEntity<?> putTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(todo));
    }

    @DeleteMapping("/todos")
    public ResponseEntity<?> deleteTodo(@RequestParam Integer todoId) {
        return ResponseEntity.ok(todoService.deleteTodo(todoId));
    }
}
