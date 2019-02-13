package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.dto.UserDto;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import com.inther.services.authentication.AuthenticationService;
import com.inther.services.authentication.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final HttpHeaders httpHeaders;
    private final UserRepository userRepository;
    AuthenticationService authentication;

    @PostMapping
    public ResponseEntity<?> signUp(
            @Validated(value = {RequestDataValidator.Authentication.class})
            @RequestBody UserDto authDto)
    {
        if (userRepository.findUserByEmail(authDto.getEmail()).isEmpty()) {
            userRepository.save(
                    serviceUtilityBean.encodeUserPassword(
                            User.builder()
                                    .id(UUID.randomUUID())
                                    .email(authDto.getEmail())
                                    .password(authDto.getPassword())
                                    .role("USER")
                                    .build()
                    )
            );
            return signIn(authDto);
        } else {
            return new ResponseEntity<>("User already exists!", httpHeaders, HttpStatus.CONFLICT);
        }
    }

    @PutMapping
    public ResponseEntity<?> signIn(
            @Validated(value = {RequestDataValidator.Authentication.class})
            @RequestBody UserDto authDto)
    {

        Optional<String> _token = tokenAuthenticationService.login(authDto.getEmail(), authDto.getPassword());

        return _token.map(token -> new ResponseEntity<>(token, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("could not authorize!", httpHeaders, HttpStatus.UNAUTHORIZED));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping
    boolean signOut(@AuthenticationPrincipal final User user) {
        authentication.logout(user);
        return true;
    }

    @Autowired
    public AuthenticationController(ServiceUtilityBean serviceUtilityBean,
                                    TokenAuthenticationService tokenAuthenticationService,
                                    HttpHeaders httpHeaders,
                                    UserRepository userRepository)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.httpHeaders = httpHeaders;
        this.userRepository = userRepository;
    }
}