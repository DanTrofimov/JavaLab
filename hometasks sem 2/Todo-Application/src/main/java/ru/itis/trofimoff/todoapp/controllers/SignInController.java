package ru.itis.trofimoff.todoapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.trofimoff.todoapp.config.ApplicationInitializer;
import ru.itis.trofimoff.todoapp.dto.SignInFormDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class SignInController {

    @Autowired
    public UserService userService;

    @Autowired
    private Logger logger;

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String getSignInPage(Model model){
        model.addAttribute("signInFormDto", new SignInFormDto());
        return "sign-in";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String postSignInPage(HttpServletRequest request, @Valid SignInFormDto signInForm, BindingResult bindingResult, Model model){
        bindingResult.getAllErrors().forEach(System.out::println);
        if (!bindingResult.hasErrors()) {
            Optional<UserDto> userDto = userService.findByEmail(signInForm.getEmail());
            if (userDto.isPresent() && userService.equalsRowPasswordWithHashPassword(signInForm.getPassword(), userDto.get().getPassword())) {
                request.getSession().setAttribute("currentUser", userDto.get());
                request.getSession().setAttribute("signInError", null);
                switch (userDto.get().getRole()) {
                    case "user":
                        return "redirect:/main?page=0&size=5";
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
            model.addAttribute("signInFormDto", signInForm);
            request.getSession().setAttribute("signInError", "Incorrect input");
            return "sign-in";
//            return "redirect:/sign-in";
        }
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String exceptionExample() throws IOException {
        try {
            throw new IOException();
        } catch (IOException ex) {
            logger.error("Exception: " + ex);
        }
        return "error";
    }

}
