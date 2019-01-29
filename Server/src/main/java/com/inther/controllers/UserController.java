package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.UserDto;
import com.inther.entities.UserEntity;
import com.inther.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController
{
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> putUser(@Validated(value = {RequestDataValidator.PutRequest.class}) @RequestBody UserDto userDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(userService.putUser(modelMapper.map(userDtoToPut, UserEntity.class)));
    }

    @PatchMapping
    public ResponseEntity<?> patchUser(@Validated(value = {RequestDataValidator.PatchRequest.class}) @RequestBody UserDto userDtoToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam(value = "email") String email) throws Exception
    {
        return null;
    }

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper)
    {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
}