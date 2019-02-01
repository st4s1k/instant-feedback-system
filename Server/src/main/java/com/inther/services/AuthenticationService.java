package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.beans.ServiceUtilityBean;
import com.inther.entities.implementation.UserEntity;
import com.inther.exceptions.BadCredentialsException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final ServiceUtilityBean serviceUtilityBean;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putAuthentication(UserEntity userEntity) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        if (!optionalUserEntity.isPresent())
        {
            userRepository.save(serviceUtilityBean.encodeUserEntityPassword(serviceUtilityBean.completeRegistrationObject(userEntity)));
            responseBean.setStatus(HttpStatus.CREATED);
            responseBean.setHeaders(httpHeaders);
            responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' successfully registered");
        }
        else
        {
            throw new DuplicatedEntryException("User with email: '" + userEntity.getEmail() + "' already exists");
        }
        return responseBean;
    }
    public ResponseBean getAuthentication(String status) throws Exception
    {
        if ((status != null) && (status.equals("badCredentials")))
        {
            throw new BadCredentialsException("Invalid authentication data");
        }
        else
        {
            responseBean.setHeaders(httpHeaders);
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse("This is a authentication page, please use POST method to authenticate");
        }
        return responseBean;
    }

    @Autowired
    public AuthenticationService(UserRepository authenticationRepository, ServiceUtilityBean serviceUtilityBean, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.userRepository = authenticationRepository;
        this.serviceUtilityBean = serviceUtilityBean;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}