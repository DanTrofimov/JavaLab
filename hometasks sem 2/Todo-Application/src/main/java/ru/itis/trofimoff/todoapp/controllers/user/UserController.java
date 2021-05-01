package ru.itis.trofimoff.todoapp.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.UserDto;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.models.Todo;
import ru.itis.trofimoff.todoapp.services.group.GroupService;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;
import ru.itis.trofimoff.todoapp.utils.pagination.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    public TodoService todoService;

    @Autowired
    public GroupService groupService;

    @Autowired
    public PaginationUtil paginationUtil;

    private int pageSize = 5;
    private int currentPage = 0;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMainPage(HttpServletRequest request){
        UserDto currentUser = (UserDto) request.getSession().getAttribute("currentUser");

        pageSize = request.getParameter("size") == null ? pageSize : Integer.parseInt(request.getParameter("size"));
        currentPage = request.getParameter("page") == null ? currentPage : Integer.parseInt(request.getParameter("page"));

        List<Todo> todoObjects = todoService.getUserTodosWithPagination(currentUser.getId(), currentPage, pageSize);
        List<Group> groupObjects = groupService.getAllGroups();

        request.getSession().setAttribute("todos", todoObjects);
        request.getSession().setAttribute("groups", groupObjects);


        int todosAmount = todoService.getUsersTodosAmount(currentUser.getId());
        int pageAmount = paginationUtil.getPageAmount(todosAmount, pageSize);


        request.getSession().setAttribute("size", pageSize);
        request.getSession().setAttribute("pageAmount", pageAmount);
        request.getSession().setAttribute("currentPage", currentPage);

        return "main";
    }
}
