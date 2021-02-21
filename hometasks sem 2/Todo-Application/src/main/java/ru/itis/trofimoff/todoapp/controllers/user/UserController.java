package ru.itis.trofimoff.todoapp.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;
import ru.itis.trofimoff.todoapp.services.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public TodoService todoService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage(HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("current-user");
        List<Todo> todoObjects = todoService.getUserTodos(currentUser.getId());
//        List<Group> groupObjects = groupService.getAllGroups();

        request.getSession().setAttribute("todos", todoObjects);
//        request.getSession().setAttribute("groups", groupObjects);
        return "main";
    }
}
