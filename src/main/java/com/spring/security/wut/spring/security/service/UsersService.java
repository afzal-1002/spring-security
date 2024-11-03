package com.spring.security.wut.spring.security.service;

import com.spring.security.wut.spring.security.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    public Users addNewService(Users user);
    public Users findUsersByUserName(String userName);
    public List<Users> getAllUsers(String userName);

}