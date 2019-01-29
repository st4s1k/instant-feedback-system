package com.inther.controllers;

import com.inther.entities.ParticipantEntity;
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
    public ResponseEntity<?> putParticipant(@Validated @RequestBody ParticipantEntity participantEntityToPut) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteParticipant(@RequestParam(value = "participantId") Integer participantId) throws Exception
    {
        return null;
    }

    @Autowired
    public ParticipantController(ParticipantService participantService, ModelMapper modelMapper)
    {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }
}