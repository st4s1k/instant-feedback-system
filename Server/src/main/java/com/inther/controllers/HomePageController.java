package com.inther.controllers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.services.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomePageController
{
    private final HomePageService homePageService;

    @GetMapping
    public ResponseEntity<?> getAuthentication()
    {
        return new ResponseEntityWrapper<>(homePageService.getHomePage());
    }

    @Autowired
    public HomePageController(HomePageService homePageService)
    {
        this.homePageService = homePageService;
    }
}