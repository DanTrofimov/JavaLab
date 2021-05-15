package ru.itis.trofimoff.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.trofimoff.task.services.user.UserService;

@RestController
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/{user-id}/block")
    public ResponseEntity<?> blockUser(@PathVariable("user-id") Integer userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok().build();
    }
}
