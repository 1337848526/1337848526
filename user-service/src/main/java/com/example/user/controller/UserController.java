package com.example.user.controller;

import com.example.user.dto.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public UserInfo getUserById(@PathVariable Long id) {
        return new UserInfo(
                id,
                "User" + id,
                "user" + id + "@example.com"
        );
    }

    @GetMapping("/ping")
    public String ping() {
        return "user-service pong";
    }
}
