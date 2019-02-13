package com.inther.services.entity;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.Presentation;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;

    @Autowired
    public UserService(ServiceUtilityBean serviceUtilityBean,
                       UserRepository userRepository)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(User user)
    {
        return userRepository
                .findUserByEmail(user.getEmail())
                .map(u -> Optional.<User>empty())
                .orElseGet(() -> Optional.of(userRepository.save(serviceUtilityBean.encodeUserPassword(user))));
    }

    public List<User> fetchAllUsers()
    {
        return userRepository.findAll();
    }

    public Optional<User> fetchUserById(UUID id)
    {
        return userRepository.findUserById(id);
    }

    public Optional<Boolean> updateUserData(User user)
    {
        return userRepository
                .findUserById(user.getId())
                .map(u -> userRepository.save(user).equals(user));
    }

    public Optional<Boolean> deleteUser(UUID id)
    {
        return userRepository
                .findUserById(id)
                .map(p -> {
                    userRepository.deleteUserById(id);
                    return !userRepository.existsById(id);
                });
    }
}