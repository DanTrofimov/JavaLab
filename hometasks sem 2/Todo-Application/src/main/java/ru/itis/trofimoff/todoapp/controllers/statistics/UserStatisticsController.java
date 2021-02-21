package ru.itis.trofimoff.todoapp.controllers.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.trofimoff.todoapp.dto.UserStatisticsDto;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserStatisticsController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/user-statistics", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserStatisticsDto getAddTodo(HttpServletRequest request) {
        return userService.getUserStatistic(Integer.parseInt(request.getParameter("userId")));
    }

    @RequestMapping(value = "/user-statistics", method = RequestMethod.POST)
    public String postAddTodo(HttpServletRequest request) {
        return "redirect:" + request.getServletContext().getContextPath() + "/main";
    }
}
