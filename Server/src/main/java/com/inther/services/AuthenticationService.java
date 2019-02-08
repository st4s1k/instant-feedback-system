package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;


    public ResponseBean createUser(User user)
    {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        responseBean.setHeaders(httpHeaders);
        if (!optionalUser.isPresent())
        {
            user.setRole("ROLE_USER");
            userRepository.save(serviceUtilityBean.encodeUserEntityPassword(user));
            responseBean.setStatus(HttpStatus.CREATED);
            responseBean.setResponse("User with email: '" + user.getEmail() + "' successfully registered");
        }
        else
        {
            responseBean.setStatus(HttpStatus.CONFLICT);
            responseBean.setResponse("User with email: '" + user.getEmail() + "' already exists");
        }
        return responseBean;
    }

    public ResponseBean requestAuthData(User user)
    {
        Optional<User> optionalUserEntity = userRepository.findUserByEmail(user.getEmail());
        responseBean.setHeaders(httpHeaders);
        if (optionalUserEntity.isPresent() &&
                serviceUtilityBean.validPassword(user.getPassword(), optionalUserEntity.get()))
        {
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse(optionalUserEntity.get());
        }
        else
        {
            responseBean.setStatus(HttpStatus.NOT_FOUND);
            responseBean.setResponse("User with email: '" + user.getEmail() + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public AuthenticationService(ServiceUtilityBean serviceUtilityBean,
                                 UserRepository authenticationRepository,
                                 ResponseBean responseBean,
                                 HttpHeaders httpHeaders)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = authenticationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}