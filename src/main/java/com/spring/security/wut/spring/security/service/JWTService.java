package com.spring.security.wut.spring.security.service;

import org.springframework.stereotype.Service;

@Service
public interface JWTService  {
    public String generateToken(String userName);
}
