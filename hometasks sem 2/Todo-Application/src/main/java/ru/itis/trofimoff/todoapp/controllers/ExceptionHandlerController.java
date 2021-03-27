package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("showError", "Sorry, we got an error: ");
        modelAndView.addObject("exceptionInfo", exception.getMessage());
        return modelAndView;
    }
}
