package com.inther.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/authentication")
public class AuthenticationController
{
    @GetMapping
    public Map<String, Object> authenticate()
    {
        return null;
    }
}