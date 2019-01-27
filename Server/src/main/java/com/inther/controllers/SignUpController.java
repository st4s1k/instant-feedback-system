package com.inther.controllers;

import com.inther.dto.SignUpDto;
import com.inther.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/signUp")
public class SignUpController
{
    private final SignUpService signUpService;

    @PutMapping
    public ResponseEntity<Object> putSignUp(@Valid @RequestBody SignUpDto signUpDto) throws Exception
    {
        return null;
    }

    @Autowired
    public SignUpController(SignUpService signUpService)
    {
        this.signUpService = signUpService;
    }
}