package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
    // todo: specify exceptions
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("showError", "Sorry, we got an error: ");
        modelAndView.addObject("exceptionInfo", exception.getMessage());
        return modelAndView;
    }


}
