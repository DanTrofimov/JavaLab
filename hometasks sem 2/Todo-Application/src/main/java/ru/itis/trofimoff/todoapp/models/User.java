package ru.itis.trofimoff.todoapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    int id;
    String name;
    String email;
    String password;
    String role; // user / admin
    int allTodos;
    int doneTodos;

    // safe
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "user";
    }

    public User(String name, String email, String password, int allTodos, int doneTodos) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "user";
        this.allTodos = allTodos;
        this.doneTodos = doneTodos;
    }

    public User(SignInFormDto form) {
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = "user";
    }

    public User(SignUpFormDto form) {
        this.name = form.getUsername();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = "user";
    }
}
