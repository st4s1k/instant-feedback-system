package com.inther.repositories;

import com.inther.entities.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, UUID>
{
    Optional<MarkEntity> findMarkEntityByPresentationIdAndEmail(UUID presentationId, String email);
}