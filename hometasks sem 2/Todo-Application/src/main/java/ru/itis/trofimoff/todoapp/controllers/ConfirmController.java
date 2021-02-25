package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ConfirmController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/confirm/{code}", method = RequestMethod.GET)
    public String getMainPage(HttpServletRequest request, @PathVariable String code, Model model){

        System.out.println(code);

        userService.confirmUser(code);

        model.addAttribute("confirmMessage",  "Confirmed!");

        return "sign-in";
    }
}
