package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.ParticipantDto;
import com.inther.entities.implementation.ParticipantEntity;
import com.inther.services.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController
{
    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> putParticipant(@Validated(value = {RequestDataValidator.PutParticipant.class}) @RequestBody ParticipantDto participantDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(participantService.putParticipant(modelMapper.map(participantDtoToPut, ParticipantEntity.class)));
    }

    @DeleteMapping(value = "/{participantId}")
    public ResponseEntity<?> deleteParticipant(@PathVariable(value = "participantId") Integer participantId) throws Exception
    {
        return new ResponseEntityWrapper<>(participantService.deleteParticipant(participantId));
    }

    @Autowired
    public ParticipantController(ParticipantService participantService, ModelMapper modelMapper)
    {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }
}