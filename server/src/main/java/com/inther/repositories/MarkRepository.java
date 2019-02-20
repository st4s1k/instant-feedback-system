package com.inther.repositories;

import com.inther.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID>
{
    List<Mark> findAllByPresentation_Id(UUID presentationId);
    Optional<Mark> findAllByPresentation_IdAndUser_Id(UUID presentationId, UUID userId);

    @Transactional
    void deleteAllByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteAllByUser_Id(UUID userId);
}