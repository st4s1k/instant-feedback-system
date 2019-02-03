package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.AuthenticationDto;
import com.inther.entities.implementation.UserEntity;
import com.inther.services.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController
{
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> putAuthentication(@Validated(value = {RequestDataValidator.PutAuthentication.class}) @RequestBody AuthenticationDto authenticationDto) throws Exception
    {
        return new ResponseEntityWrapper<>(authenticationService.putAuthentication(modelMapper.map(authenticationDto, UserEntity.class)));
    }

    @GetMapping
    public ResponseEntity<?> getAuthentication(@RequestParam(name = "status", required = false) String status) throws Exception
    {
        return new ResponseEntityWrapper<>(authenticationService.getAuthentication(status));
    }

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, ModelMapper modelMapper)
    {
        this.authenticationService = authenticationService;
        this.modelMapper = modelMapper;
    }
}