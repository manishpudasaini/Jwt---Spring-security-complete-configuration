package com.jwt.jwtlearn.controller;

import com.jwt.jwtlearn.entity.AuthRequest;
import com.jwt.jwtlearn.entity.AuthResponse;
import com.jwt.jwtlearn.entity.User;
import com.jwt.jwtlearn.service.AuthService;
import com.jwt.jwtlearn.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/everyone")
public class MyController {
    private final UserService userService;
    private final AuthService authService;

    public MyController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest){
      return new ResponseEntity<>(authService.generateToken(authRequest),HttpStatus.OK) ;
    }
}
