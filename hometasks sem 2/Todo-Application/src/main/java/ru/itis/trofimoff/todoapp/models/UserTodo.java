package ru.itis.trofimoff.todoapp.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTodo {
    public int userId;
    public int todoId;
}
