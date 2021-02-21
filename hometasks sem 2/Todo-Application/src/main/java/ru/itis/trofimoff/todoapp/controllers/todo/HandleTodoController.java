package ru.itis.trofimoff.todoapp.controllers.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.models.User;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HandleTodoController {

    @Autowired
    public TodoService todoService;

    @RequestMapping(value = "/handle-todo", method = RequestMethod.GET)
    public String getHandleTodo(HttpServletRequest request) {
        return "redirect:" + request.getServletContext().getContextPath() + "/main";
    }

    @RequestMapping(value = "/handle-todo", method = RequestMethod.POST)
    public String postHandleTodo(HttpServletRequest request) {
        String text = request.getParameter("change-todo-text");
        int todoId = Integer.parseInt(request.getParameter("todo-id"));
        User currentUser = (User) request.getSession().getAttribute("current-user");
        int userId = currentUser.getId();
        switch (request.getParameter("todo-action")) {
            case "change":
                if (!text.trim().equals("")) {
                    Todo todo = new Todo(todoId, text);
                    todoService.updateTodo(todo);
                }
                break;
            case "remove":
                todoService.deleteTodo(todoId, userId);
                break;
        }
        return "redirect:" + request.getServletContext().getContextPath() + "/main";
    }
}
