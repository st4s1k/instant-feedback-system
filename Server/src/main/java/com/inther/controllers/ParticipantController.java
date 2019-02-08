package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.ParticipantDto;
import com.inther.entities.ParticipantEntity;
import com.inther.services.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController
{
    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addParticipant(@Validated(value = {RequestDataValidator.PutParticipant.class}) @RequestBody ParticipantDto participantDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(participantService.addParticipant(modelMapper.map(participantDtoToPut, ParticipantEntity.class)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable(value = "id") UUID id) throws Exception
    {
        return new ResponseEntityWrapper<>(participantService.deleteParticipant(id));
    }

    @Autowired
    public ParticipantController(ParticipantService participantService, ModelMapper modelMapper)
    {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }
}