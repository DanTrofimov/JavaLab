package ru.itis.trofimoff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping(value = "/")
    public String redirectSignInPage() {
        return "redirect:/sign-in";
    }

    @GetMapping(value = "/sign-in")
    public String getSignInPage() {
        System.out.println("hello!");
        return "sign-in";
    }

    @GetMapping(value = "/profile")
    public String getProfilePage() {
        return "profile";
    }
}
