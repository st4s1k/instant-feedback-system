package com.inther.services;

import com.inther.repositories.UserRepository;
import com.inther.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MiscService
{
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRoleRepository userRoleRepository;

    public void userRegister(String email, String password)
    {
        userRepository.addUser(email, bCryptPasswordEncoder.encode(password), 1);
        userRoleRepository.addUserRole(email, "ROLE_USER");
    }

    @Autowired
    public MiscService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRoleRepository userRoleRepository)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepository = userRoleRepository;
    }
}