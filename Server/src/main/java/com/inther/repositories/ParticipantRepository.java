package com.inther.repositories;

import com.inther.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, UUID>
{
    Optional<ParticipantEntity> findParticipantEntityByPresentationIdAndEmail(UUID presentationId, String email);
    Optional<ParticipantEntity> findParticipantEntityById(UUID id);

    @Transactional
    void deleteParticipantEntityById(UUID id);
}