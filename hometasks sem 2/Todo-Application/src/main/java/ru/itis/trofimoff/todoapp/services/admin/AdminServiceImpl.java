package ru.itis.trofimoff.todoapp.services.admin;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.AdminTodoDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private UserService userService;
    private TodoService todoService;

    public AdminServiceImpl(UserService userService, TodoService todoService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    // на самом деле здесь написан быдлокод, как и в принципе во всем остальном проекте, но конкретно здесь он берет свое начало
    // fixme: пофиксить dto для Todo model, отсутствует поле groupId
    @Override
    public void addTodoForSeveralUsers(AdminTodoDto adminDto) {
        Todo adminTodo = new Todo(adminDto.getTodoText());
        int[] users = new int[adminDto.getUsers().length];
        adminTodo.setGroupId(2); // admin
        for (int i = 0; i < users.length; i++) {
            users[i] = Integer.parseInt(adminDto.getUsers()[i]);
        }

        todoService.addTodo(adminTodo);

        for (int i = 0; i < users.length; i++) {
            todoService.addUsersTodo(adminTodo, users[i], "admin");
        }
    }
}
