package com.spring.security.wut.spring.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class JwtController {

    @GetMapping("/getSessionId")
    public ResponseEntity<String> getSessionId(HttpServletRequest httpServletRequest)
    {
        return ResponseEntity.ok("Session id " + httpServletRequest.getSession().getId());

    }

    @GetMapping("/getCsrfToke")
    public ResponseEntity<CsrfToken> getCrfToken(HttpServletRequest httpServletRequest)
    {
        return ResponseEntity.ok((CsrfToken) httpServletRequest.getAttribute("_csrf")) ;

    }
}
