package ru.itis.trofimoff.todoapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// JPA
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private int id;

    @OneToMany(mappedBy = "group")
    List<Todo> todos;
}
