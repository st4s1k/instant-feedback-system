package com.inther.services;

import com.inther.beans.AdminAuthorityValidator;
import com.inther.domain.User;
import com.inther.domain.UserAuthority;
import com.inther.exceptions.SelfDeleteException;
import com.inther.repositories.UserAuthorityRepository;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private UserRepository userRepository;
    private UserAuthorityRepository userAuthorityRepository;
    private AdminAuthorityValidator adminAuthorityValidator;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean addUser(User userToAdd)
    {
        if (adminAuthorityValidator.validateAdminAuthority())
        {
            userRepository.addUser(userToAdd.getEmail(), bCryptPasswordEncoder.encode(userToAdd.getPassword()), userToAdd.getEnabled());
            for (UserAuthority userAuthority : userToAdd.getUserAuthorities())
            {
                userAuthorityRepository.addUserAuthority(userAuthority.getEmail(), userAuthority.getAuthority());
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean editUser(String email, User userToEdit)
    {
        return null;
    }

    public Boolean deleteUser(String email) throws SelfDeleteException
    {
        if (adminAuthorityValidator.validateAdminAuthority())
        {
            if (!((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername().equals(email))
            {
                userRepository.deleteUser(email);
            }
            else
            {
                throw new SelfDeleteException();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Autowired
    public UserService(UserRepository userRepository, UserAuthorityRepository userAuthorityRepository, AdminAuthorityValidator adminAuthorityValidator, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.adminAuthorityValidator = adminAuthorityValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}