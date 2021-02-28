package ru.itis.trofimoff.todoapp.models;

import lombok.*;
import ru.itis.trofimoff.todoapp.dto.TodoDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    int id;
    String text;
    int groupId;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(TodoDto todoDto) {
        this.text = todoDto.getTodoText();
        if (todoDto.getGroup() != 0) this.groupId = todoDto.getGroup();
        if (todoDto.getId() != 0) this.id = todoDto.getId();
    }

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
