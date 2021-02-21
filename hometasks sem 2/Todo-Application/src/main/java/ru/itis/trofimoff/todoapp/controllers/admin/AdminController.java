package ru.itis.trofimoff.todoapp.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.trofimoff.todoapp.services.admin.AdminService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    public AdminService adminService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminPage(HttpServletRequest request){
        request.getSession().setAttribute("all-users", adminService.getAllUsers());
        return "admin";
    }
}
