package ru.itis.trofimoff.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.trofimoff.todoapp.models.User;

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

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.allTodos = user.getAllTodos();
        this.doneTodos = user.getDoneTodos();
    }
}
