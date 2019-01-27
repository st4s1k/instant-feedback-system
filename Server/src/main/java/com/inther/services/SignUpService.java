package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUpService
{
    private final UserRepository userRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseEntity<Object> putSignUp()
    {
        return null;
    }

    @Autowired
    public SignUpService(UserRepository userRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.userRepository = userRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}