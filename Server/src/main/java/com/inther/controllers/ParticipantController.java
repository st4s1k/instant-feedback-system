package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.services.entity.ParticipantService;
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

@RestController
@RequestMapping(value = "/api/participants")
public class ParticipantController
{
    private final HttpHeaders httpHeaders;
    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public List<ParticipantDto> getPresentationParticipants(@PathVariable String id) {
        return participantService
                .fetchPresentationParticipants(UUID.fromString(id))
                .stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<?> putParticipant(
            @Validated(value = {RequestDataValidator.AddParticipant.class})
            @RequestBody ParticipantDto participantDtoToPut)
    {
        Participant newParticipant = modelMapper.map(participantDtoToPut, Participant.class);
        Optional<UUID> newParticipantId = participantService.addParticipant(newParticipant);

        return newParticipantId.isPresent()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.CREATED)
                : new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable String id)
    {
        Optional<Boolean> participantRemovalStatus = participantService.deleteParticipant(UUID.fromString(id));

        return participantRemovalStatus.map(deleted -> deleted
                ? new ResponseEntity<>(httpHeaders, HttpStatus.OK)
                : new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public ParticipantController(HttpHeaders httpHeaders, ParticipantService participantService, ModelMapper modelMapper)
    {
        this.httpHeaders = httpHeaders;
        this.participantService = participantService;
        this.modelMapper = modelMapper;
    }
}