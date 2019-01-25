package com.inther.services;

import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MiscService
{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userRegister(String email, String password)
    {
        userRepository.addUser(email, bCryptPasswordEncoder.encode(password), 1);
    }

    @Autowired
    public MiscService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}