package ru.itis.trofimoff.todoapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserTodo {
    public int userId;
    public int todoId;
}
