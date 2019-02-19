package com.inther.services.authentication;

import com.google.common.collect.ImmutableMap;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.dto.UserDto;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import com.inther.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenAuthenticationService implements AuthenticationService
{
    private final TokenService tokens;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public TokenAuthenticationService(TokenService tokens,
                                      ServiceUtilityBean serviceUtilityBean,
                                      UserRepository userRepository,
                                      UserMapper userMapper)
    {
        this.tokens = tokens;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public int register(UserDto authDto) {
        if (!userRepository.findByEmail(authDto.getEmail()).isPresent()) {
            userRepository.save(
                    serviceUtilityBean.encodeUserPassword(
                            User.builder()
                                    .email(authDto.getEmail())
                                    .password(authDto.getPassword())
                                    .role("USER")
                                    .build()));
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public Optional<UserDto> login(String email, String password) {

        return userRepository
                .findByEmail(email)
                .filter(user -> serviceUtilityBean.isPasswordValid(password, user))
                .map(userMapper::toDto)
                .map(userDto -> {
                    userDto.setToken(tokens.generate(ImmutableMap.of("email", email)));
                    return userDto;
                });

    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("email"))
                .flatMap(userRepository::findByEmail);
    }

    @Override
    public void logout(User user) {
        // nothing to do yet
    }
}