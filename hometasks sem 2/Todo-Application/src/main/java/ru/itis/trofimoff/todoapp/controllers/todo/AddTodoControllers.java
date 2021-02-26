package ru.itis.trofimoff.todoapp.controllers.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AddTodoControllers {

    @Autowired
    public TodoService todoService;

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String getAddTodo(HttpServletRequest request) {
        return "redirect:/main";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String postAddTodo(HttpServletRequest request, @Valid TodoDto todoDto, BindingResult bindingResult) {
        UserDto currentUser = (UserDto) request.getSession().getAttribute("currentUser");
        if (!bindingResult.hasErrors()) { // Todo: validate todo
            todoService.addUsersTodo(new Todo(todoDto), currentUser.getId(), "users");
        }
        return "redirect:/main";
    }
}
