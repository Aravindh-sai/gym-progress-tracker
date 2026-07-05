package com.aravindh.gymtracker.controller;
import org.springframework.web.bind.annotation.RestController;
import com.aravindh.gymtracker.service.UserService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aravindh.gymtracker.dto.LoginRequest;
import com.aravindh.gymtracker.dto.LoginResponse;
import com.aravindh.gymtracker.dto.SignupRequest;
import com.aravindh.gymtracker.dto.SignupResponse;

@RestController
public class UserController {
    private final UserService userService;
    public UserController( UserService userService){
        this.userService = userService;
    }
@PostMapping("/signup")
public SignupResponse  signup(@RequestBody SignupRequest request) {
    return userService.signup(
        request.getName(),
        request.getEmail(),
        request.getPassword(),
        request.getPreferredUnit()
    );
}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return userService.login( request.getEmail() , request.getPassword() );
    }
}

