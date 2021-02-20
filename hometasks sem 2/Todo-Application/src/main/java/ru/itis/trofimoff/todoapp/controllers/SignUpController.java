package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistrationPage(HttpServletRequest request, SignUpFormDto signUpForm) {
        if (true) { // form is valid
            userService.saveUser(signUpForm);
            return "redirect:" + request.getServletContext().getContextPath() + "/sign-in";
        } else {
            return "redirect:" + request.getServletContext().getContextPath() + "/registration";
        }
    }
}
