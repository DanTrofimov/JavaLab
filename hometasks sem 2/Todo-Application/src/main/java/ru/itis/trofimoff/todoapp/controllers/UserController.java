package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

@Component
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getRegistrationPage(){
        return "main";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistrationPage(SignUpFormDto signUpForm) {
        if (true) { // form is valid
            userService.saveUser(signUpForm);
            return "redirect:/main";
        } else {
            return "redirect:/registration";
        }
    }
}
