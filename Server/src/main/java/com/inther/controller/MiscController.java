package com.inther.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MiscController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, Object> welcomePage()
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        requestResultMap.put("Status", "OK");
        requestResultMap.put("Message", "You successfully signed in. Now you have access to API.");
        requestResultMap.put("Authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return requestResultMap;
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.PUT)
    public Map<String, Object> userRegister(@RequestParam(value = "email") String email,
                                            @RequestParam(value = "password") String password)
    {
        return null;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Map<String, Object> userLogin()
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        requestResultMap.put("Status", "OK");
        requestResultMap.put("Message", "This is Login page. Please use POST method to login");
        return requestResultMap;
    }

    @RequestMapping(value = "/userLoginError", method = RequestMethod.GET)
    public Map<String, Object> userLoginError()
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        requestResultMap.put("Status", "ERROR");
        requestResultMap.put("Message", "Bad credentials");
        return requestResultMap;
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public Map<String, Object> accessDenied()
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        requestResultMap.put("Status", "ERROR");
        requestResultMap.put("Message", "Access denied for your authority");
        return requestResultMap;
    }
}