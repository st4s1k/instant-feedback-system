package com.inther.services.entity;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.entities.Participant;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository,
                              AuthorityUtilityBean authorityUtilityBean)
    {
        this.participantRepository = participantRepository;
        this.authorityUtilityBean = authorityUtilityBean;
    }

    public boolean addParticipant(Participant participant)
    {
        return participantRepository.findParticipantByPresentationIdAndEmail(
                        participant.getPresentationId(),
                        participant.getEmail()
                ).isEmpty()
                && participantRepository.save(participant).equals(participant);
    }

    public Optional<Boolean> deleteParticipant(UUID id)
    {
        return participantRepository.findParticipantById(id)
                .filter(p -> authorityUtilityBean.getCurrentUserId().equals(id)
                        || authorityUtilityBean.validateAdminAuthority())
                .map(p -> {
                    participantRepository.deleteParticipantById(id);
                    return !participantRepository.existsById(id);
                });
    }
}