package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.UserDto;
import com.inther.entities.User;
import com.inther.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/users")
public class UserController
{
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper,
                          HttpHeaders httpHeaders)
    {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpHeaders = httpHeaders;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @Validated(value = {RequestDataValidator.AddUser.class})
            @RequestBody UserDto userDto)
    {
        return userService
                .createUser(modelMapper.map(userDto, User.class))
                .map(user -> new ResponseEntity<>(httpHeaders, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers()
    {
        List<UserDto> userDtoList = userService
                .fetchAllUsers().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDtoList, httpHeaders,
                userDtoList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") String id)
    {
        return userService
                .fetchUserById(UUID.fromString(id))
                .map(user -> modelMapper.map(user, UserDto.class))
                .map(userDto -> new ResponseEntity<>(userDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<?> editUser(
            @Validated(value = {RequestDataValidator.UpdateUser.class})
            @RequestBody UserDto userDto)
    {
        return userService
                .updateUserData(dtoToEntity(userDto))
                .map(editedUser -> new ResponseEntity<>(editedUser, httpHeaders, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") String id)
    {
        return userService
                .deleteUser(UUID.fromString(id))
                .map(deleted -> new ResponseEntity<>(httpHeaders, deleted ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    private User dtoToEntity(UserDto userDto) {
        return userService
                .fetchUserById(UUID.fromString(userDto.getId()))
                .map(foundUser -> modelMapper.map(userDto, User.class).setId(foundUser.getId()))
                .orElseGet(() ->  modelMapper.map(userDto, User.class));
    }
}