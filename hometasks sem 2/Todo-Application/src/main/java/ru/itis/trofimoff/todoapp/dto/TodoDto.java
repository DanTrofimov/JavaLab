package ru.itis.trofimoff.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itis.trofimoff.todoapp.models.Todo;

import javax.validation.constraints.NotBlank;

@Data
public class TodoDto {
    @NotBlank
    String todoText;
    int id = 0;
    int group = 0;

    public TodoDto(String text) {
        this.todoText = text;
    }
}
