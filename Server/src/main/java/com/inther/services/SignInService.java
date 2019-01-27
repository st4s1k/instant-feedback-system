package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.exceptions.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class SignInService
{
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean getSignIn(String status) throws Exception
    {
        if (status == null)
        {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            responseBean.setHeaders(httpHeaders);
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse("Welcome to sign in page, please use POST method");
        }
        else if (status.equals("badCredentials"))
        {
            throw new BadCredentialsException("Bad credentials");
        }
        return responseBean;
    }

    @Autowired
    public SignInService(ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}