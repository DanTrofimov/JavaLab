package ru.itis.trofimoff.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trofimoff.annotations.ModelTable;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@ModelTable(tableName = "users")
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
}