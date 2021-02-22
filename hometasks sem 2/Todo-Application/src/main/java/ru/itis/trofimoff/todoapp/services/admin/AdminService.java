package ru.itis.trofimoff.todoapp.services.admin;

import ru.itis.trofimoff.todoapp.dto.AdminTodoDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
    void addTodoForSeveralUsers(AdminTodoDto adminTodoDto);
}
