package com.inther.controllers;

import com.inther.dto.UserDto;
import com.inther.entities.UserEntity;
import com.inther.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController
{
    private final UserService userService;

    @PutMapping
    public ResponseEntity<Object> putUser(@Valid @RequestBody UserDto userDtoToPut) throws Exception
    {
        return null;
    }

   @PatchMapping
    public ResponseEntity<Object> patchUser(@Valid @RequestBody UserEntity userEntityToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "email") String email) throws Exception
    {
        return null;
    }

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
}