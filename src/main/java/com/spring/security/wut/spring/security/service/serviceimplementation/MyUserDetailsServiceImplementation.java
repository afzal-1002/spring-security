package com.spring.security.wut.spring.security.service.serviceimplementation;

import com.spring.security.wut.spring.security.model.MyUserDetails;
import com.spring.security.wut.spring.security.model.Users;
import com.spring.security.wut.spring.security.service.MyUserDetailsService;
import com.spring.security.wut.spring.security.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsServiceImplementation implements MyUserDetailsService {


    public static final Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImplementation.class);

    @Autowired
    UsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = this.usersService.findUsersByUserName(username);

        if(users == null)
        {
            logger.warn("User Not found {}", username);
            throw new UsernameNotFoundException("User do not exist");
        }else {
            logger.info("User found: {}" , username);
            return new MyUserDetails(users);
        }
    }

}
