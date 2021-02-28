package ru.itis.trofimoff.todoapp.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    private String name;
    private int id;
}
