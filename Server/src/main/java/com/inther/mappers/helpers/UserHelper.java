package com.inther.mappers.helpers;

import com.inther.entities.User;
import com.inther.repositories.UserRepository;
import org.mapstruct.Named;

import java.util.UUID;

public class UserHelper {

    private final UserRepository userRepository;

    public UserHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Named("userIdToEntity")
    public User userIdToEntity(UUID userId) {
        return userRepository.findUserById(userId).orElse(null);
    }
}
