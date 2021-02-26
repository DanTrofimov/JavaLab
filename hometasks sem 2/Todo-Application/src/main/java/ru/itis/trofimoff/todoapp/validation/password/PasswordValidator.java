package ru.itis.trofimoff.todoapp.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() > 6 && value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*")
                && value.matches(".*[0-9].*");
    }
}