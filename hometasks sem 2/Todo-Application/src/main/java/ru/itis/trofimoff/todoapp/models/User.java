package ru.itis.trofimoff.todoapp.models;

import lombok.*;
import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

// JPA
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String role; // user / admin
    private int allTodos;
    private int doneTodos;

    private Boolean confirmed;
    private String confirmCode;

    @ManyToMany
    @JoinTable(name = "users_todos",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "todos_id", referencedColumnName = "id")})
    private List<Todo> todos;

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
        this.name = form.getName();
        this.email = form.getEmail();
        this.password = form.getPassword();
        this.role = "user";
    }
}
