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
                request.getSession().setAttribute("sign-in-error", null);
                switch (userDto.get().getRole()) {
                    case "user":
                        return "redirect:" + request.getServletContext().getContextPath() + "/main";
                    case "admin":
                        return "redirect:" + request.getServletContext().getContextPath() + "/adminExample";
                    default:
                        return "redirect:" + request.getServletContext().getContextPath() + "/sign-in";
                }
            } else {
                request.getSession().setAttribute("sign-in-error", "Unknown user");
                return "redirect:" + request.getServletContext().getContextPath() + "/sign-in";
            }
        } else {
            request.getSession().setAttribute("sign-in-error", "Incorrect input");
            return "redirect:" + request.getServletContext().getContextPath() + "/sign-in";
        }
    }
}
