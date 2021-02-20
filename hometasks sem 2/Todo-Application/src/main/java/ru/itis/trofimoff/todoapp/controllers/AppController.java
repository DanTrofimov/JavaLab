package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

@Controller
public class AppController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistrationPage(SignUpFormDto signUpForm){
        userService.saveUser(signUpForm);
        return "registration";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String getSignInPage(){
        return "sign-in";
    }


}
