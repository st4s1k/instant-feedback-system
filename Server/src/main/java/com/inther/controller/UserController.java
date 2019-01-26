package com.inther.controller;

import com.inther.domain.User;
import com.inther.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user")
public class UserController
{
    private final UserService userService;

    @PutMapping
    public Map<String, Object> addUser(@RequestBody User userToAdd)
    {
        return null;
    }

   @PatchMapping
    public Map<String, Object> editUser(@RequestBody User userToEdit)
    {
        return null;
    }

    @DeleteMapping
    public Map<String, Object> deleteUser(@RequestParam(value = "email") String email)
    {
        return null;
    }

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
}