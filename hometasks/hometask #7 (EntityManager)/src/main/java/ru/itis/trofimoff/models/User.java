package ru.itis.trofimoff.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean hash;
}