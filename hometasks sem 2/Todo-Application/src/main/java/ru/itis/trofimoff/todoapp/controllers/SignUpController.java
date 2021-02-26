package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model){
        model.addAttribute("signUpForm", new SignUpFormDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistrationPage(@Valid SignUpFormDto signUpForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) { // TODO: add signUpForm validation
            userService.saveUser(signUpForm);
            return "redirect:/sign-in";
        } else {
            model.addAttribute("signUpForm", signUpForm);
            return "registration";
//            return "redirect:" + request.getServletContext().getContextPath() + "/registration";
        }
    }
}
