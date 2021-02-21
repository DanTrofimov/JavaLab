package ru.itis.trofimoff.todoapp.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.TodoDto;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.services.admin.AdminService;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminAddTodoController {

    @Autowired
    public TodoService todoService;

    @Autowired
    public AdminService adminService;

    @RequestMapping(value = "/admin-add", method = RequestMethod.POST)
    public String postAdminAddPage(HttpServletRequest request){ // adminTodoDto mb
        String text = request.getParameter("todo-text");
        Todo adminTodo = new Todo(text);

        if (request.getParameterValues("users") != null
                && request.getParameter("todo-text").trim() != "") {
            adminService.addTodoForSeveralUsers(request.getParameterValues("users"), adminTodo);
        }

        return "redirect:" + request.getServletContext().getContextPath() + "/admin-add";
    }

    @RequestMapping(value = "/admin-add", method = RequestMethod.GET)
    public String getAdminAddPage(HttpServletRequest request){
        return "/admin-add";
    }
}
