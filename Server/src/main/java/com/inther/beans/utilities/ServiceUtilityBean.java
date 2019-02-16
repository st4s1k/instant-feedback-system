package com.inther.beans.utilities;

import com.inther.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtilityBean
{
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User encodeUserPassword(User user)
    {
        if (user.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        return user;
    }

    public boolean isPasswordValid(String rawPassword, User user) {
        return bCryptPasswordEncoder.matches(rawPassword, user.getPassword());
    }

    @Autowired
    public ServiceUtilityBean(BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}