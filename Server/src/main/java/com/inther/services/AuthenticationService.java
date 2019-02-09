package com.inther.services;

import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.dto.AuthenticationDto;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final HttpHeaders httpHeaders;


    public ResponseEntity<?> createUser(AuthenticationDto authDto)
    {
        return userRepository
                .findUserByEmail(authDto.getEmail())
                .<ResponseEntity<?>>map(user -> new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT))
                .orElseGet(() -> {
                    userRepository.save(serviceUtilityBean.encodeUserPassword(new User(authDto)));
                    return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
                });
    }

    public ResponseEntity<?> validateUserCredentials(AuthenticationDto authDto)
    {
        return userRepository
                .findUserByEmail(authDto.getEmail())
                .filter(user -> serviceUtilityBean.isPasswordValid(authDto.getPassword(), user))
                .<ResponseEntity<?>>map(user -> new ResponseEntity<>(user, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.FORBIDDEN));
    }

    @Autowired
    public AuthenticationService(ServiceUtilityBean serviceUtilityBean,
                                 UserRepository authenticationRepository,
                                 HttpHeaders httpHeaders)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = authenticationRepository;
        this.httpHeaders = httpHeaders;
    }
}