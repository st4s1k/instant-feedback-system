package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.entities.implementation.UserEntity;
import com.inther.exceptions.BadCredentialsException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final ServiceUtilityBean serviceUtilityBean;
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
        userEntity.setUserAuthorities(setRegistrationObjectAuthorityValues(userEntity.getEmail()));
        return userEntity;
    }

    public ResponseBean putAuthentication(UserEntity userEntity) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        if (!optionalUserEntity.isPresent())
        {
            userRepository.save(serviceUtilityBean.encodeUserEntityPassword(completeRegistrationObject(userEntity)));
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