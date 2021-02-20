package ru.itis.trofimoff.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInFormDto {
    private String email;
    private String password; // need to replace by hashPassword
}
