package com.inther.repositories;

import com.inther.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID>
{
    Optional<Participant> findParticipantEntityByPresentationIdAndEmail(UUID presentationId, String email);
    Optional<Participant> findParticipantEntityById(UUID id);

    @Transactional
    void deleteParticipantEntityById(UUID id);
}