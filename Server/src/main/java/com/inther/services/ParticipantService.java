package com.inther.services;

import com.inther.domain.Participant;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService
{
    private ParticipantRepository participantRepository;

    public void addParticipant(Participant participantToAdd)
    {
        participantRepository.save(participantToAdd);
    }

    public Boolean deleteParticipant(Integer participantId)
    {
        participantRepository.deleteParticipantByParticipantId(participantId);
        return null;
    }

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository)
    {
        this.participantRepository = participantRepository;
    }
}