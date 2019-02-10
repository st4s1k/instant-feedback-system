package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.AuthenticationDto;
import com.inther.dto.UserDto;
import com.inther.entities.User;
import com.inther.services.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController
{
    private final AuthenticationService authenticationService;
    private final HttpHeaders httpHeaders;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    HttpHeaders httpHeaders,
                                    ModelMapper modelMapper)
    {
        this.authenticationService = authenticationService;
        this.httpHeaders = httpHeaders;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> signUp(
            @Validated(value = {RequestDataValidator.Authentication.class})
            @RequestBody AuthenticationDto authenticationDto)
    {
        return authenticationService.registerNewUserAttempt(modelMapper.map(authenticationDto, User.class))
                .map(newUser -> new ResponseEntity<>("User " + newUser.getEmail() + "successfully created!",
                        httpHeaders, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT));
    }

    @PutMapping
    public ResponseEntity<?> signIn(
            @Validated(value = {RequestDataValidator.Authentication.class})
            @RequestBody AuthenticationDto authenticationDto)
    {
        return authenticationService.validateUserCredentials(authenticationDto)
                .map(user -> new ResponseEntity<>(modelMapper.map(user, UserDto.class), httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.FORBIDDEN));
    }
}