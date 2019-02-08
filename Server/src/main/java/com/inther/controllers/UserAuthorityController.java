package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.UserAuthorityDto;
import com.inther.entities.UserAuthorityEntity;
import com.inther.services.UserAuthorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/userAuthority")
public class UserAuthorityController
{
    private final UserAuthorityService userAuthorityService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addUserAuthority(@Validated(value = {RequestDataValidator.PutUserAuthority.class}) @RequestBody UserAuthorityDto userAuthorityDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(userAuthorityService.addUserAuthority(modelMapper.map(userAuthorityDtoToPut, UserAuthorityEntity.class)));
    }

    @DeleteMapping(value = {"", "/{id}"})
    public ResponseEntity<?> deleteUserAuthority(@PathVariable(value = "id") UUID id) throws Exception
    {
        return new ResponseEntityWrapper<>(userAuthorityService.deleteUserAuthority(id));
    }

    @Autowired
    public UserAuthorityController(UserAuthorityService userAuthorityService, ModelMapper modelMapper)
    {
        this.userAuthorityService = userAuthorityService;
        this.modelMapper = modelMapper;
    }
}