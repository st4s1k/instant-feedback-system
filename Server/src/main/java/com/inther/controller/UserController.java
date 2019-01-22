package com.inther.controller;

import com.inther.domain.User;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class UserController
{
    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    public Map<String, Object> addUser(@RequestBody User userToAdd)
    {
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam(value = "userEmail") String userEmail,
                                     @RequestParam(value = "userPassword") String userPassword)
    {
        return null;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.PATCH)
    public Map<String, Object> editUser(@RequestBody User userToUpdate)
    {
        return null;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public Map<String, Object> deleteUser(@RequestParam(value = "userId") String userId)
    {
        return null;
    }
}