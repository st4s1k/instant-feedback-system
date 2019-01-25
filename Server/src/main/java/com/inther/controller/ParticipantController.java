package com.inther.controller;

import com.inther.domain.Participant;
import com.inther.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ParticipantController
{
    private ParticipantService participantService;

    @RequestMapping(value = "/addParticipant", method = RequestMethod.PUT)
    public Map<String, Object> addParticipant(@RequestBody Participant participantToAdd)
    {
        participantService.addParticipant(participantToAdd);
        Map<String, Object> requestResultMap = new HashMap<>();
        requestResultMap.put("Status", "OK");
        requestResultMap.put("Message", "Participant was added to presentation with id: " + participantToAdd.getPresentationId());
        return requestResultMap;
    }

    @RequestMapping(value = "/deleteParticipant", method = RequestMethod.DELETE)
    public Map<String, Object> deleteParticipant(@RequestParam(value = "participantId") Integer participantId)
    {
        Map<String, Object> requestResultMap = new HashMap<>();
        if (participantService.deleteParticipant(participantId))
        {
            requestResultMap.put("Status", "OK");
            requestResultMap.put("Message", "Participant with id: " + participantId + " was deleted");
        }
        else
        {
            requestResultMap.put("Status", "ERROR");
            requestResultMap.put("Message", "Access denied for your authority");
        }
        return requestResultMap;
    }

    @Autowired
    public ParticipantController(ParticipantService participantService)
    {
        this.participantService = participantService;
    }
}