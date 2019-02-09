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

import java.util.UUID;

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
            @Validated(value = {RequestDataValidator.PutUser.class})
            @RequestBody UserDto userDto)
    {
        return userService
                .createUserAttempt(modelMapper.map(userDto, User.class))
                .map(user -> new ResponseEntity<>(httpHeaders, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") String id)
    {
        return userService
                .searchForRequestedUser(UUID.fromString(id))
                .map(user -> modelMapper.map(user, UserDto.class))
                .map(userDto -> new ResponseEntity<>(userDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<?> editUser(
            @Validated(value = {RequestDataValidator.PatchUser.class})
            @RequestBody UserDto userDto)
    {

        return userService
                .updateUserData(modelMapper.map(userDto, User.class))
                .map(edited -> new ResponseEntity<>(httpHeaders, edited ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT))
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
}