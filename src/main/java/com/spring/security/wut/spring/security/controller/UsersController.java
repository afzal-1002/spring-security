package com.spring.security.wut.spring.security.controller;

import com.spring.security.wut.spring.security.model.Users;
import com.spring.security.wut.spring.security.repository.UsersRepository;
import com.spring.security.wut.spring.security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public String getHome()
    {
        return "<h1> Welcome to my HomeLearning </h1>";
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<Users> addNewUser(@RequestBody Users users)
    {
        return ResponseEntity.ok(this.usersService.addNewUser(users)) ;
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users users)
    {
        Users users1 = this.usersService.verifyUsers(users);

        return ResponseEntity.ok(this.usersService.addNewUser(users)) ;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Users>> getAllUsers()
    {
        return ResponseEntity.ok(this.usersService.getAllUsers()) ;
    }
}
