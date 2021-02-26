package ru.itis.trofimoff.todoapp.dto;

import lombok.Data;
import ru.itis.trofimoff.todoapp.validation.pair.ValidFields;
import ru.itis.trofimoff.todoapp.validation.password.ValidPassword;

import javax.validation.constraints.Email;

@Data
@ValidFields(
        message = "{errors.different.fields}",
        firstField = "password",
        secondField = "repeatPassword"
)
public class SignUpFormDto {
    private String name;
    @Email(message = "{error.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
    private String repeatPassword;
    private String userAgreement;
}