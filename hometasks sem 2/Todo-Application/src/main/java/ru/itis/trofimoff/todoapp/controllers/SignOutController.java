package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignOutController {
    @RequestMapping(value = "/sign-out", method = RequestMethod.GET)
    public String getSignInPage(HttpServletRequest request){
        request.getSession().setAttribute("currentUser", null);
        return "redirect:" + request.getServletContext().getContextPath() + "/sign-in";
    }
}
