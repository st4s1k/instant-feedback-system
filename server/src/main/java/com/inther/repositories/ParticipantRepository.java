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
    Optional<Participant> findByPresentation_IdAndEmail(UUID presentationId, String participantemal);
    List<Participant> findAllByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteAllByPresentation_Id(UUID presentationId);
}