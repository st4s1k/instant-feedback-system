package com.inther.services.entity;

import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(ServiceUtilityBean serviceUtilityBean,
                       UserRepository userRepository,
                       ModelMapper modelMapper)
    {
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
                .map(foundUser -> {
                    foundUser.setPassword(foundUser.getPassword().equals(user.getPassword())
                            ? foundUser.getPassword()
                            : serviceUtilityBean.encodeUserPassword(user).getPassword());
                    modelMapper.map(user, foundUser);
                    return userRepository.save(foundUser).equals(user);
                });
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