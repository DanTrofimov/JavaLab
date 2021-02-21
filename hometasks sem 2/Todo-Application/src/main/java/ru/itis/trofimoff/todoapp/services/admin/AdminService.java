package ru.itis.trofimoff.todoapp.services.admin;

import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;

import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    void addTodoForSeveralUsers(String[] usersStr, Todo todo);
}
