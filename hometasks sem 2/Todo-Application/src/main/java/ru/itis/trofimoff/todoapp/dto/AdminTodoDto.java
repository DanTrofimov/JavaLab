package ru.itis.trofimoff.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class AdminTodoDto {
    private String[] users;
    private String todoText;
}
