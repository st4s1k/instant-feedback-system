package com.inther.repositories;

import com.inther.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer>
{
    @Modifying
    @Transactional
    Participant save(Participant participantToAdd);

    @Modifying
    @Transactional
    void deleteParticipantByParticipantId(Integer participantId);
}