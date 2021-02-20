package ru.itis.trofimoff.todoapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Todo {

    int id;
    String text;
    int groupId;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
