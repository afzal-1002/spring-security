package com.spring.security.wut.spring.security.service.serviceimplementation;

import com.spring.security.wut.spring.security.model.MyUserDetails;
import com.spring.security.wut.spring.security.model.Users;
import com.spring.security.wut.spring.security.repository.UsersRepository;
import com.spring.security.wut.spring.security.service.JWTService;
import com.spring.security.wut.spring.security.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImplementation implements UsersService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImplementation.class);

    @Autowired
    UsersRepository usersRepository;


    @Autowired
    JWTService jwtService;

    @Lazy
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Users addNewUser(Users user) {
        user.setJwtToken(this.jwtService.generateToken(user.getUserName()));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.usersRepository.save(user);
    }

    @Override
    public Users findUsersByUserName(String userName) {
        return this.usersRepository.findUsersByUserName(userName);
    }

    @Override
    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    @Override
    public Users verifyUsers(Users users) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        users.getUserName(),
                        users.getPassword()
                )
        );
        if (authentication.isAuthenticated())
        {
            return users;
        }else {
            logger.warn("User could not log in {}", users.getUserName());
            return null;
        }

    }
}
