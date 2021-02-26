package ru.itis.trofimoff.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class SignUpFormDto {
    private String name;
    @Email(message = "{error.incorrect.email}")
    private String email;
    private String password;
    private String repeatPassword;
    private String userAgreement;
}