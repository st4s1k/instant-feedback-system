package com.inther.controllers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.services.HomePageService;
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
    public ResponseEntity<Object> getHomePage()
    {
        return new ResponseEntityWrapper<>(homePageService.getHomePage());
    }

    public HomePageController(HomePageService homePageService)
    {
        this.homePageService = homePageService;
    }
}