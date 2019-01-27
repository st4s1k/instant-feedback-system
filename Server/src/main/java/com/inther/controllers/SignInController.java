package com.inther.controllers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signIn")
public class SignInController
{
    private final SignInService signInService;

    @GetMapping
    public ResponseEntity<Object> getSignIn(@RequestParam(name = "status", required = false) String status) throws Exception
    {
        return new ResponseEntityWrapper<>(signInService.getSignIn(status));
    }

    @Autowired
    public SignInController(SignInService signInService)
    {
        this.signInService = signInService;
    }
}