package ru.itis.trofimoff.todoapp.dto;

import lombok.Data;
import ru.itis.trofimoff.todoapp.validation.pair.ValidFields;
import ru.itis.trofimoff.todoapp.validation.password.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ValidFields(
        message = "{errors.different.fields}",
        firstField = "password",
        secondField = "repeatPassword"
)
public class SignUpFormDto {
    @NotBlank(message = "{errors.blank.name}")
    private String name;
    @Email(message = "{error.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
    private String repeatPassword;
    @NotBlank(message = "{errors.blank.agreement}")
    private String userAgreement;
}