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
    Optional<Participant> findParticipantByPresentationIdAndEmail(UUID presentationId, String userEmail);
    Optional<Participant> findParticipantById(UUID id);
    Optional<Participant> findParticipantByEmail(String email);
    List<Participant> findParticipantsByPresentationId(UUID presentationId);

    @Transactional
    void deleteParticipantById(UUID id);

    @Transactional
    void deleteParticipantByPresentationIdAndEmail(UUID presentationId, String userEmail);
}