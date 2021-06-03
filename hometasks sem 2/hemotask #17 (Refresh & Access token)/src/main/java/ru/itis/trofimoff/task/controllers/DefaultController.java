package ru.itis.trofimoff.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itis.trofimoff.task.dto.EmailPasswordDto;
import ru.itis.trofimoff.task.dto.TokensDto;
import ru.itis.trofimoff.task.services.login.LoginService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @Autowired
    public LoginService loginService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/todos";
    }

    @PostMapping("/login")
    public ResponseEntity<TokensDto> login(@RequestBody EmailPasswordDto emailPassword, HttpServletRequest request) {
        String token = request.getHeader("X-TOKEN");
        System.out.println("X-TOKEN: " + token);
        return token != null ? ResponseEntity.ok(loginService.refresh(token)) : ResponseEntity.ok(loginService.login(emailPassword));
    }
}
