package com.inther.repositories;

import com.inther.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID>
{
    Optional<Participant> findParticipantByPresentation_IdAndEmail(UUID presentationId, String participantemal);
    Optional<Participant> findParticipantById(UUID id);
    Optional<Participant> findParticipantByEmail(String email);
    List<Participant> findParticipantsByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteParticipantById(UUID id);

    @Transactional
    void deleteParticipantByPresentation_IdAndEmail(UUID presentationId, String userEmail);

    @Transactional
    void deleteParticipantsByPresentation_Id(UUID presentationId);
}