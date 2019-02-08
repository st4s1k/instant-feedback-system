package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.AuthenticationDto;
import com.inther.entities.UserEntity;
import com.inther.services.AuthenticationService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated(value = {RequestDataValidator.PutAuthentication.class}) @RequestBody AuthenticationDto authenticationDto)
    {
        return new ResponseEntityWrapper<>(authenticationService.createUser(modelMapper.map(authenticationDto, UserEntity.class)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Validated(value = {RequestDataValidator.PutAuthentication.class}) @RequestBody AuthenticationDto authenticationDto)
    {
        return new ResponseEntityWrapper<>(authenticationService.requestAuthData(modelMapper.map(authenticationDto, UserEntity.class)));
    }

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, ModelMapper modelMapper)
    {
        this.authenticationService = authenticationService;
        this.modelMapper = modelMapper;
    }
}