package ru.itis.trofimoff.todoapp.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.AdminTodoDto;
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
    public String postAdminAddPage(HttpServletRequest request, AdminTodoDto adminDto){

//        if (request.getParameterValues("users") != null
//                && request.getParameter("todoText").trim() != "") {
        if (true) { // TODO: validete adminDto
            adminService.addTodoForSeveralUsers(adminDto);
        }

        return "redirect:/admin-add";
    }

    @RequestMapping(value = "/admin-add", method = RequestMethod.GET)
    public String getAdminAddPage(HttpServletRequest request){
        return "/admin-add";
    }
}
