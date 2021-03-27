package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignUpFormDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRegistrationPageEmptyPathMapping(Model model){
        model.addAttribute("signUpFormDto", new SignUpFormDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPageDefaultMapping(Model model){
        model.addAttribute("signUpFormDto", new SignUpFormDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegistrationPageDefaultMapping(@Valid SignUpFormDto signUpForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("signUpFormDto.ValidFields")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("signUpFormDto", signUpForm);
            return "registration";
        }
        userService.saveUser(signUpForm);
        return "redirect:/sign-in";
    }
}
