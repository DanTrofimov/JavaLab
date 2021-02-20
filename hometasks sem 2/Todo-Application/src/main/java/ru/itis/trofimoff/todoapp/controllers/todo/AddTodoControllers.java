package ru.itis.trofimoff.todoapp.controllers.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.repositories.TodoRepository;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;
import ru.itis.trofimoff.todoapp.services.todo.TodoServiceImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddTodoControllers {

    @Autowired
    public TodoServiceImpl todoService; // fixme: replace by interface

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String getSignInPage() {
        return "main";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String postSignInPage(HttpServletRequest request, TodoDto todoDto) {
        // add todo_ here
        User currentUser = (User) request.getSession().getAttribute("current-user");
        todoService.addUsersTodo(todoDto, currentUser.getId(), "users");
        return "main";
    }
}
