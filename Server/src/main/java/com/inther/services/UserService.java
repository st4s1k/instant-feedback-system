package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;

    @Autowired
    public UserService(AuthorityUtilityBean authorityUtilityBean,
                       ServiceUtilityBean serviceUtilityBean,
                       UserRepository userRepository)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
    }

    public Optional<User> createUserAttempt(User user)
    {
        return userRepository
                .findUserByEmail(user.getEmail())
                .filter(p -> authorityUtilityBean.validateAdminAuthority())
                .map(p -> p.setEmail(authorityUtilityBean.getCurrentAuthenticationEmail()))
                .map(serviceUtilityBean::encodeUserPassword)
                .map(userRepository::save);
    }

    public Optional<User> searchForRequestedUser(UUID id)
    {
        return userRepository.findUserById(id);
    }

    public Optional<Boolean> updateUserData(User user)
    {
        return userRepository.findUserById(user.getId())
                .filter(u -> authorityUtilityBean.getCurrentAuthenticationEmail().equals(u.getEmail())
                        || authorityUtilityBean.validateAdminAuthority())
                .map(u -> userRepository.exists(Example.of(u.updateBy(user))));
    }

    public Optional<Boolean> deleteUser(UUID id)
    {
        return userRepository.findUserById(id)
                .filter(p -> authorityUtilityBean.getCurrentAuthenticationEmail().equals(p.getEmail())
                        || authorityUtilityBean.validateAdminAuthority())
                .map(p -> {
                    userRepository.deleteUserById(id);
                    return !userRepository.existsById(id);
                });
    }
}