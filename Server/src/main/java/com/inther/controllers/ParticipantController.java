package com.inther.controllers;

import com.inther.dto.ParticipantDto;
import com.inther.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController
{
    private final ParticipantService participantService;

    @PutMapping
    public ResponseEntity<Object> putParticipant(@Valid @RequestBody ParticipantDto participantDtoToPut) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteParticipant(@Valid @RequestParam(value = "participantId") Integer participantId) throws Exception
    {
        return null;
    }

    @Autowired
    public ParticipantController(ParticipantService participantService)
    {
        this.participantService = participantService;
    }
}