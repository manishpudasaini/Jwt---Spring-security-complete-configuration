package com.jwt.jwtlearn.controller;

import com.jwt.jwtlearn.entity.User;
import com.jwt.jwtlearn.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.allUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user1 = userService.getUserById(id);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
}
