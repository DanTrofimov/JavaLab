package ru.itis.trofimoff.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpFormDto {
    private String name;
    private String email;
    private String password;
    private String repeatPassword; // need to replace by hashPassword
    private String userAgreement;
}