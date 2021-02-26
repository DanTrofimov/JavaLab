package ru.itis.trofimoff.todoapp.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.dto.AdminTodoDto;
import ru.itis.trofimoff.todoapp.services.admin.AdminService;
import ru.itis.trofimoff.todoapp.services.todo.TodoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AdminAddTodoController {

    @Autowired
    public TodoService todoService;

    @Autowired
    public AdminService adminService;

    @RequestMapping(value = "/admin-add", method = RequestMethod.POST)
    public String postAdminAddPage(HttpServletRequest request, @Valid AdminTodoDto adminDto, BindingResult bindingResult){

//        if (request.getParameterValues("users") != null
//                && request.getParameter("todoText").trim() != "") {
        if (!bindingResult.hasErrors()) {
            adminService.addTodoForSeveralUsers(adminDto);
        }

        return "redirect:/admin-add";
    }

    @RequestMapping(value = "/admin-add", method = RequestMethod.GET)
    public String getAdminAddPage(HttpServletRequest request){
        return "/admin-add";
    }
}
