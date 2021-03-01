package ru.itis.trofimoff.todoapp.models;

import lombok.*;
import ru.itis.trofimoff.todoapp.dto.TodoDto;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;

    @ManyToMany(mappedBy = "users") // mb fix
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "group_id")
    public Group group;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(TodoDto todoDto) {
        this.text = todoDto.getTodoText();
        if (todoDto.getGroup() != 0) this.group.setId(todoDto.getGroup());
        if (todoDto.getId() != 0) this.id = todoDto.getId();
    }

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getGroupId() {
        return this.group.getId();
    }

    public void setGroupId(int id) {
        this.group.setId(id);
    }
}