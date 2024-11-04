package com.spring.security.wut.spring.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JWTService  {
    public String generateToken(String userName);
    public String extractUserName(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
