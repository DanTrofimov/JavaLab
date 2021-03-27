package ru.itis.trofimoff.todoapp.controllers.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FilterTodoController {

    @Autowired
    public TodoService todoService;

    @RequestMapping(value = "/filter-todos", method = RequestMethod.GET)
    public String getFilterTodo(HttpServletRequest request) {
        int groupId = Integer.parseInt(request.getParameter("group"));
        UserDto currentUser = (UserDto) request.getSession().getAttribute("currentUser");
        List<Todo> todos = todoService.getUserTodosByGroup(currentUser.getId(), groupId);

        request.getSession().setAttribute("todos", todos);
        return "/main";
    }

    @RequestMapping(value = "/filter-todos", method = RequestMethod.POST)
    public String postFilterTodo() {
        return "/main";
    }
}
