package com.bren.carshop.controller;

import com.bren.carshop.dto.request.UserRequest;
import com.bren.carshop.dto.response.AuthenticationResponse;
import com.bren.carshop.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody UserRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@Valid @RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping("/checkToken")
    public void checkToken() {
    }

    @PreAuthorize("authentication.principal == #text && hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/test")
    public void test(String text) {
        System.out.println("find cart of " + text);

    }
}

