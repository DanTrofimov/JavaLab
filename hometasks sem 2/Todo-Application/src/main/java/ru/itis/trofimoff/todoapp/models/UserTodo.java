package ru.itis.trofimoff.todoapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserTodo {
    public int userId;
    public int todoId;
}
