package ru.itis.trofimoff.todoapp.services.admin;

import org.springframework.stereotype.Component;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

    private UserService userService;
    private TodoService todoService;

    public AdminServiceImpl(UserService userService, TodoService todoService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @Override
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    // на самом деле здесь написан быдлокод, как и в принципе во всем остальном проекте, но конкретно здесь он берет свое начало
    @Override
    public void addTodoForSeveralUsers(String[] usersStr, Todo adminTodo) {
        int[] users = new int[usersStr.length];
        adminTodo.setGroupId(2); // admin
        for (int i = 0; i < users.length; i++) {
            users[i] = Integer.parseInt(usersStr[i]);
        }

        todoService.addTodo(adminTodo);

        for (int i = 0; i < users.length; i++) {
            todoService.addUsersTodo(adminTodo, users[i], "admin");
        }
    }
}
