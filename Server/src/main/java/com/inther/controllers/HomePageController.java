package com.inther.controllers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.beans.AuthorityUtilityBean;
import com.inther.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomePageController
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAuthentication() throws Exception
    {
        return new ResponseEntityWrapper<>(userService.getUser(authorityUtilityBean.getCurrentAuthenticationEmail()));
    }

    @Autowired
    public HomePageController(AuthorityUtilityBean authorityUtilityBean, UserService userService)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.userService = userService;
    }
}