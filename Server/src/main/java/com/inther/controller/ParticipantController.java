package com.inther.controller;

import com.inther.domain.Participant;
import com.inther.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController
{
    private final ParticipantService participantService;

    @PutMapping
    public Map<String, Object> addParticipant(@RequestBody Participant participantToAdd)
    {
        return null;
    }

    @DeleteMapping
    public Map<String, Object> deleteParticipant(@RequestParam(value = "participantId") Integer participantId)
    {
        return null;
    }

    @Autowired
    public ParticipantController(ParticipantService participantService)
    {
        this.participantService = participantService;
    }
}