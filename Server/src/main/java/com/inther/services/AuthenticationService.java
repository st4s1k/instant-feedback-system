package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.entities.implementation.UserEntity;
import com.inther.exceptions.BadCredentialsException;
import com.inther.exceptions.BadRequestException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    private List<UserAuthorityEntity> setRegistrationObjectAuthorityValues(String userAuthorityEmail)
    {
        List<UserAuthorityEntity> userAuthorityEntityList = new ArrayList<>();
        UserAuthorityEntity userAuthorityEntity = new UserAuthorityEntity();
        userAuthorityEntity.setEmail(userAuthorityEmail);
        userAuthorityEntity.setAuthority("ROLE_ADMIN");
        userAuthorityEntityList.add(userAuthorityEntity);
        return userAuthorityEntityList;
    }
    private UserEntity completeRegistrationObject(UserEntity userEntity)
    {
        userEntity.setEnabled(1);
        userEntity.setAuthorities(setRegistrationObjectAuthorityValues(userEntity.getEmail()));
        return userEntity;
    }

    public ResponseBean createUser(UserEntity userEntity)
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        responseBean.setHeaders(httpHeaders);
        if (!optionalUserEntity.isPresent())
        {
            userRepository.save(serviceUtilityBean.encodeUserEntityPassword(completeRegistrationObject(userEntity)));
            responseBean.setStatus(HttpStatus.CREATED);
            responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' successfully registered");
        }
        else
        {
            responseBean.setStatus(HttpStatus.CONFLICT);
            responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' already exists");
        }
        return responseBean;
    }

    public ResponseBean requestAuthData(UserEntity userEntity)
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        responseBean.setHeaders(httpHeaders);
        if (optionalUserEntity.isPresent())
        {
            userRepository.save(serviceUtilityBean.encodeUserEntityPassword(completeRegistrationObject(userEntity)));
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' successfully registered");
        }
        else
        {
            responseBean.setStatus(HttpStatus.NOT_FOUND);
            responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public AuthenticationService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                                 UserRepository authenticationRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = authenticationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}