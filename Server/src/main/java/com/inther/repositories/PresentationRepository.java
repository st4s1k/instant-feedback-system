package com.inther.repositories;

import com.inther.entities.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, UUID>
{
    Optional<List<PresentationEntity>> findPresentationEntityByEmail(String email);

    Optional<PresentationEntity> findPresentationEntityByTitle(String title);
    Optional<PresentationEntity> findPresentationEntityById(UUID id);

    @Transactional
    void deletePresentationEntityById(UUID id);
}