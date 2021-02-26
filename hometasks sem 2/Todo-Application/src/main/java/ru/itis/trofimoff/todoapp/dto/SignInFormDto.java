package ru.itis.trofimoff.todoapp.dto;

import lombok.Data;
import ru.itis.trofimoff.todoapp.validation.password.ValidPassword;

import javax.validation.constraints.Email;

@Data
public class SignInFormDto {
    @Email(message = "{error.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
}
