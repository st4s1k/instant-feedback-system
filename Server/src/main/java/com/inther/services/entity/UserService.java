package com.inther.services.entity;

import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.User;
import com.inther.repositories.MarkRepository;
import com.inther.repositories.MessageRepository;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import com.inther.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private final PresentationRepository presentationRepository;
    private final MessageRepository messageRepository;
    private final MarkRepository markRepository;
    private final ServiceUtilityBean serviceUtilityBean;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(PresentationRepository presentationRepository,
                       MessageRepository messageRepository,
                       MarkRepository markRepository,
                       ServiceUtilityBean serviceUtilityBean,
                       UserRepository userRepository,
                       UserMapper userMapper)
    {
        this.presentationRepository = presentationRepository;
        this.messageRepository = messageRepository;
        this.markRepository = markRepository;
        this.serviceUtilityBean = serviceUtilityBean;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
                    userMapper.patchEntity(user, foundUser);
                    return userRepository.save(foundUser).equals(user);
                });
    }

    public Optional<Boolean> deleteUser(UUID id)
    {
        // Delete all user marks
        markRepository.deleteMarksByUser_Id(id);
        // Delete all user messages
        messageRepository.deleteMessagesByUser_Id(id);
        // Delete all user presentations
        presentationRepository.deletePresentationsByUser_Id(id);
        return userRepository
                .findUserById(id)
                .map(p -> {
                    // Delete user
                    userRepository.deleteUserById(id);
                    return !userRepository.existsById(id);
                });
    }
}