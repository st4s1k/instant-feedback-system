package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.beans.ResponseBean;
import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.services.ParticipantService;
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
@RequestMapping(value = "/api/participant")
public class ParticipantController
{
    private final ParticipantService participantService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;

    @Autowired
    public ParticipantController(ParticipantService participantService,
                                 ModelMapper modelMapper,
                                 HttpHeaders httpHeaders)
    {
        this.participantService = participantService;
        this.modelMapper = modelMapper;
        this.httpHeaders = httpHeaders;
    }

    @PostMapping
    public ResponseEntity<?> addParticipant(
            @Validated(value = {RequestDataValidator.AddParticipant.class})
            @RequestBody ParticipantDto participantDto)
    {
        return participantService.addParticipant(modelMapper.map(participantDto, Participant.class)) ?
                new ResponseEntity<>(httpHeaders, HttpStatus.CREATED) :
                new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable String id)
    {
        return participantService
                .deleteParticipant(UUID.fromString(id))
                .map(deleted -> new ResponseEntity<>(httpHeaders, deleted ? HttpStatus.ACCEPTED : HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }
}