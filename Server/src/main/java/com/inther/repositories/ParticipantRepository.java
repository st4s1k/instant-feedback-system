package com.inther.repositories;

import com.inther.entities.implementation.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Integer>
{
    Optional<ParticipantEntity> findParticipantEntityByPresentationIdAndEmail(Integer presentationId, String email);
    Optional<ParticipantEntity> findParticipantEntityById(Integer id);

    @Modifying
    @Transactional
    void deleteParticipantEntityById(Integer id);
}