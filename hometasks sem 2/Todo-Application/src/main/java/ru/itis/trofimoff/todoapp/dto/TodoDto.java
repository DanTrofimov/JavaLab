package ru.itis.trofimoff.todoapp.dto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class TodoDto {
    @NotBlank
    String todoText;
    int id = 0;
    int group = 0;

    public TodoDto(String text) {
        this.todoText = text;
    }
}
