package ru.itis.trofimoff.todoapp.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AppValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
