package ru.itis.trofimoff.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class SignInController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String getSignInPage(){
        return "sign-in";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String getSignInPage(HttpServletRequest request, SignInFormDto signInForm){
        if (true) { // TODO: signInForm validation
            Optional<UserDto> userDto = userService.findByEmail(signInForm.getEmail());
            if (userDto.isPresent() && userService.equalsRowPasswordWithHashPassword(signInForm.getPassword(), userDto.get().getPassword())) {
                request.getSession().setAttribute("currentUser", userDto.get());
                request.getSession().setAttribute("signInError", null);
                switch (userDto.get().getRole()) {
                    case "user":
                        return "redirect:/main";
                    case "admin":
                        return "redirect:/admin";
                    default:
                        return "redirect:/sign-in";
                }
            } else {
                request.getSession().setAttribute("signInError", "Unknown user");
                return "redirect:/sign-in";
            }
        } else {
            request.getSession().setAttribute("signInError", "Incorrect input");
            return "redirect:/sign-in";
        }
    }
}
