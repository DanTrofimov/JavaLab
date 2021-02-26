package ru.itis.trofimoff.todoapp.services.admin;

import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.dto.AdminTodoDto;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
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
    @Override
    public void addTodoForSeveralUsers(AdminTodoDto adminDto) {
        System.out.println(adminDto);

        if (adminDto.getTodoText().equals("") || adminDto.getUsers() == null) return;
        TodoDto adminTodoDto = new TodoDto(adminDto.getTodoText());
        int[] users = new int[adminDto.getUsers().length];
        adminTodoDto.setGroup(2); // admin, changed _todo's group
        for (int i = 0; i < users.length; i++) {
            users[i] = Integer.parseInt(adminDto.getUsers()[i]);
        }

        // got _todo's id
        todoService.addTodo(adminTodoDto);

        for (int i = 0; i < users.length; i++) {
            todoService.addUsersTodo(adminTodoDto, users[i], "admin");
        }
    }
}
