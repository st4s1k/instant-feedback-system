package com.inther.controller;

import com.inther.domain.User;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class UserController
{
    @RequestMapping(value = "/userRegister", method = RequestMethod.PUT)
    public Map<String, Object> userRegister(@RequestParam(value = "userEmail") String userEmail,
                                         @RequestParam(value = "userPassword") String userPassword)
    {
        return null;
    }

    @RequestMapping(value = "/user/editSelfUser", method = RequestMethod.PATCH)
    public Map<String, Object> editSelfUser(@RequestBody User userToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/admin/addNewUser", method = RequestMethod.PUT)
    public Map<String, Object> addNewUser(@RequestBody User userToAdd)
    {
        return null;
    }

    @RequestMapping(value = "/admin/editAnyUser", method = RequestMethod.PATCH)
    public Map<String, Object> editAnyUser(@RequestBody User userToEdit)
    {
        return null;
    }

    @RequestMapping(value = "/admin/deleteAnyUser", method = RequestMethod.DELETE)
    public Map<String, Object> deleteAnyUser(@RequestParam(value = "userId") String userId)
    {
        return null;
    }
}