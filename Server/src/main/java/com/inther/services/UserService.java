package com.inther.services;

import com.inther.domain.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private UserRepository userRepository;

    public Boolean addUser(User userToAdd)
    {
        userRepository.addUser(userToAdd.getEmail(), userToAdd.getPassword(), userToAdd.getEnabled());
        return true;
    }

    public Boolean editUser(String email, User userToEdit)
    {
        return null;
    }

    public Boolean deleteUser(String email)
    {
        return null;
    }

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}