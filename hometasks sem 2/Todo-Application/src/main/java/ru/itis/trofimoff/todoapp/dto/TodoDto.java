package ru.itis.trofimoff.todoapp.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    @NotBlank
    String todoText;
    int id = 0;
    int group = 0;

    public TodoDto(String text) {
        this.todoText = text;
    }
}
