package com.inther.repositories;

import com.inther.entities.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID>
{
    Optional<Mark> findMarkEntityByPresentationIdAndEmail(UUID presentationId, String email);
}