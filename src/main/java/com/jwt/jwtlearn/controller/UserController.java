package com.jwt.jwtlearn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
public class UserController {
    @GetMapping("/user/welcome")
    public ResponseEntity<String> greetingUser(){
        return new ResponseEntity<>("Hello this is user,Manish", HttpStatus.OK);
    }

}
