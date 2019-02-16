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
    List<Mark> findMarksByPresentation_Id(UUID presentationId);
    Optional<Mark> findMarkByUser_Id(UUID userId);
    Optional<Mark> findMarkByPresentation_IdAndUser_Id(UUID presentationId, UUID userId);

    @Transactional
    void deleteMarkById(UUID id);

    @Transactional
    void deleteMarkByUser_Id(UUID id);

    @Transactional
    void deleteMarksByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteMarksByUser_Id(UUID userId);
}