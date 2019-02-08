package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.AuthenticationDto;
import com.inther.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController
{
    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @Validated(value = {RequestDataValidator.PutAuthentication.class})
            @RequestBody AuthenticationDto authenticationDto)
    {
        return authenticationService.createUser(authenticationDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @Validated(value = {RequestDataValidator.PutAuthentication.class})
            @RequestBody AuthenticationDto authenticationDto)
    {
        return authenticationService.validateUserCredentials(authenticationDto);
    }

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }
}