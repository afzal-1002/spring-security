package com.spring.security.wut.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UsersController {

    @GetMapping("/")
    public String getHome()
    {
        return "<h1> Welcome to my HomeLearning </h1>";
    }
}
