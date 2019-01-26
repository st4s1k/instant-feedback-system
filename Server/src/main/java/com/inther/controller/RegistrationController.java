package com.inther.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/registration")
public class RegistrationController
{
    @PutMapping
    public Map<String, Object> register(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password)
    {
        return null;
    }
}