package ru.itis.trofimoff.todoapp.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.services.user.UserService;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage(){
        return "main";
    }
}
