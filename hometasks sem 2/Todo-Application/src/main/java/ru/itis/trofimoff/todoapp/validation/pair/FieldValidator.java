package ru.itis.trofimoff.todoapp.validation.pair;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldValidator implements ConstraintValidator<ValidFields, Object> {

    private String firstField;
    private String secondField;

    @Override
    public void initialize(ValidFields constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object name = new BeanWrapperImpl(value).getPropertyValue(firstField); // получили конкретные значения
        Object surname = new BeanWrapperImpl(value).getPropertyValue(secondField);

        return name != null && name.equals(surname);
    }
}
