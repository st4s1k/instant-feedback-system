package com.inther.services.entity;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.entities.Participant;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<UUID> addParticipant(Participant participant)
    {
        Optional<Participant> similarParticipant = participantRepository
                .findParticipantByPresentationIdAndEmail(participant.getPresentationId(), participant.getEmail());

        return similarParticipant.isPresent()
                ? Optional.empty()
                : Optional.of(participantRepository.save(participant).getId());
    }

    public List<Participant> fetchPresentationParticipants(UUID presentationId) {
        return participantRepository.findParticipantsByPresentationId(presentationId);
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