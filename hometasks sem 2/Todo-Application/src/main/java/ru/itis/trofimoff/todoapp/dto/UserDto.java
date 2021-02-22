package ru.itis.trofimoff.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    int id;
    String name;
    String email;
    String password;
    String role; // user / admin
    int allTodos;
    int doneTodos;
}
