package com.spring.security.wut.spring.security.service.serviceimplementation;

import com.spring.security.wut.spring.security.model.Users;
import com.spring.security.wut.spring.security.service.JWTService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static javax.crypto.KeyGenerator.getInstance;

@Service
public class JWTServiceImplementation implements JWTService {

    private String secreteKey = "";


    public JWTServiceImplementation() throws NoSuchAlgorithmException {
      try {
          KeyGenerator keyGenerator =  getInstance("HmacSHA256");
          keyGenerator.init(256);
          SecretKey sk =  keyGenerator.generateKey();
          secreteKey =  Base64.getEncoder().encodeToString(sk.getEncoded());
      }catch (NoSuchAlgorithmException e)
      {
          throw  new RuntimeException(" No Such Algorithm ");
      }
    }

    public SecretKey getKey()
    {
        byte[] keyBytes = Base64.getDecoder().decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 40))
                .and()
                .signWith(getKey())
                .compact();
    }


}
