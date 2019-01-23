package com.inther.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    public String hi(){
        return "Hello world!";
    }
}
