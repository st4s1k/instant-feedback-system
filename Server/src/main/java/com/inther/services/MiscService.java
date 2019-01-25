package com.inther.services;

import com.inther.repositories.UserRepository;
import com.inther.repositories.UserAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MiscService
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAuthorityRepository userAuthorityRepository;

    public void userRegister(String email, String password)
    {
        userRepository.addUser(email, bCryptPasswordEncoder.encode(password), 1);
        userAuthorityRepository.addUserAuthority(email, "ROLE_USER");
    }

    @Autowired
    public MiscService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserAuthorityRepository userAuthorityRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAuthorityRepository = userAuthorityRepository;
    }
}