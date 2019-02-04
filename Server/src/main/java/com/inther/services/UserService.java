package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.implementation.UserAuthorityEntity;
import com.inther.entities.implementation.UserEntity;
import com.inther.exceptions.*;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    private UserEntity setUserEntityNestedAuthorityEmails(UserEntity userEntity)
    {
        for (UserAuthorityEntity userAuthorityEntity : userEntity.getAuthorities())
        {
            userAuthorityEntity.setEmail(userEntity.getEmail());
        }
        return userEntity;
    }

    public ResponseBean putUser(UserEntity userEntity) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        if (!optionalUserEntity.isPresent())
        {
            if (authorityUtilityBean.validateAdminAuthority())
            {
                userRepository.save(serviceUtilityBean.encodeUserEntityPassword(setUserEntityNestedAuthorityEmails(userEntity)));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' successfully putted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new DuplicatedEntryException("User with email: '" + userEntity.getEmail() + "' already exists");
        }
        return responseBean;
    }
    public ResponseEntity<?> getUser(String email) throws Exception
    {
        ResponseEntity<?> responseEntity;
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(email);
        if (optionalUserEntity.isPresent())
        {
            responseEntity = new ResponseEntity<>(optionalUserEntity.get(), httpHeaders, HttpStatus.OK);
        }
        else
        {
            throw new NotFoundEntryException("User with email: '" + email + "' not found");
        }
        return responseEntity;
    }
    public ResponseBean patchUser(UserEntity userEntity) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(userEntity.getEmail());
        if (optionalUserEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(userEntity.getEmail())
                    || authorityUtilityBean.validateAdminAuthority())
            {
                userRepository.save(serviceUtilityBean.encodeUserEntityPassword(serviceUtilityBean
                        .patchEntity(optionalUserEntity.get(), userEntity)));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("User with email: '" + userEntity.getEmail() + "' successfully patched");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("User with email: '" + userEntity.getEmail() + "' not found");
        }
        return responseBean;
    }
    public ResponseBean deleteUser(String email) throws Exception
    {
        Optional<UserEntity> optionalUserEntity = userRepository.findUserEntityByEmail(email);
        if (optionalUserEntity.isPresent())
        {
            if (!authorityUtilityBean.getCurrentAuthenticationEmail().equals(email)
                    && authorityUtilityBean.validateAdminAuthority())
            {
                userRepository.deleteUserEntityByEmail(email);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("User with email: '" + email + "' successfully deleted");
            }
            else if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(email)
                    && authorityUtilityBean.validateAdminAuthority())
            {
                throw new SelfDestructionException("You cannot delete yourself");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("User with email: " + email + " not found");
        }
        return responseBean;
    }

    @Autowired
    public UserService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                       UserRepository userRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}