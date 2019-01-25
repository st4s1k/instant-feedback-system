package com.inther.controller;

import com.inther.domain.User;
import com.inther.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class UserController
{
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    public Map<String, Object> addUser(@RequestBody User userToAdd)
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        if (userService.addUser(userToAdd))
        {
            requestResultMap.put("Status", "OK");
            requestResultMap.put("Message", "User " + userToAdd.getEmail() + " was successfully added");
        }
        else
        {
            requestResultMap.put("Status", "ERROR");
            requestResultMap.put("Message", "Access denied for your authority");
        }
        return requestResultMap;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.PATCH)
    public Map<String, Object> editUser(@RequestBody User userToEdit)
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        if (userService.editUser(userToEdit.getEmail(), userToEdit))
        {
            requestResultMap.put("Status", "OK");
            requestResultMap.put("Message", "User " + userToEdit.getEmail() + " was successfully edited");
        }
        else
        {
            requestResultMap.put("Status", "ERROR");
            requestResultMap.put("Message", "Access denied for your authority");
        }
        return requestResultMap;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public Map<String, Object> deleteUser(@RequestParam(value = "email") String email)
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        if (userService.deleteUser(email))
        {
            requestResultMap.put("Status", "OK");
            requestResultMap.put("Message", "User " + email + " was successfully deleted");
        }
        else
        {
            requestResultMap.put("Status", "ERROR");
            requestResultMap.put("Message", "Access denied for your authority");
        }
        return requestResultMap;
    }

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
}