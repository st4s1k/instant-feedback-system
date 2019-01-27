package com.inther.services;

import com.inther.beans.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class HomePageService
{
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean getHomePage()
    {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseBean.setHeaders(httpHeaders);
        responseBean.setStatus(HttpStatus.OK);
        responseBean.setResponse("You successfully signed in, as " + ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return responseBean;
    }

    @Autowired
    public HomePageService(ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}