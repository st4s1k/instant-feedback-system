package com.inther.services;

import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.dto.AuthenticationDto;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AuthenticationService(ServiceUtilityBean serviceUtilityBean,
                                 UserRepository authenticationRepository,
                                 UserService userService)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = authenticationRepository;
        this.userService = userService;
    }

    public Optional<User> registerNewUserAttempt(User user) {
        // Maybe some custom logic
        return userService.createUser(user);
    }

    public Optional<User> validateUserCredentials(AuthenticationDto authDto)
    {
        return userRepository
                .findUserByEmail(authDto.getEmail())
                .filter(user -> serviceUtilityBean.isPasswordValid(authDto.getPassword(), user));
    }
}