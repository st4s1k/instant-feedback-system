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
    List<Mark> findMarksByPresentationId(UUID presentationId);
    Optional<Mark> findMarkByUserId(UUID userId);
    Optional<Mark> findMarkByPresentationIdAndUserId(UUID presentationId, UUID userId);

    @Transactional
    void deleteMarkById(UUID id);

    @Transactional
    void deleteMarkByUserId(UUID id);
}