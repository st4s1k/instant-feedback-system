package com.inther.services.authentication;

import com.google.common.collect.ImmutableMap;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenAuthenticationService implements AuthenticationService
{
    private final TokenService tokens;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;

    @Autowired
    public TokenAuthenticationService(TokenService tokens,
                                      ServiceUtilityBean serviceUtilityBean,
                                      UserRepository userRepository)
    {
        this.tokens = tokens;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> login(String email, String password) {
        return userRepository
                .findUserByEmail(email)
                .filter(user -> serviceUtilityBean.isPasswordValid(password, user))
                .map(user -> tokens.generate(ImmutableMap.of("email", email)));
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("email"))
                .flatMap(userRepository::findUserByEmail);
    }

    @Override
    public void logout(User user) {
        // nothing to do yet
    }
}