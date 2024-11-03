package com.spring.security.wut.spring.security.service.serviceimplementation;

import com.spring.security.wut.spring.security.model.Users;
import com.spring.security.wut.spring.security.repository.UsersRepository;
import com.spring.security.wut.spring.security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImplementation implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users addNewService(Users user) {
        return this.usersRepository.save(user);
    }

    @Override
    public Users findUsersByUserName(String userName) {
        return this.usersRepository.findUsersByUserName(userName);
    }

    @Override
    public List<Users> getAllUsers(String userName) {
        return this.usersRepository.findAll();
    }
}
