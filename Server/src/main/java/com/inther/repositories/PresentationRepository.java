package com.inther.repositories;

import com.inther.entities.implementation.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, Integer>
{
    Optional<PresentationEntity> findPresentationEntityByTitle(String title);
    Optional<PresentationEntity> findPresentationEntityById(Integer id);

    Optional<List<PresentationEntity>> findPresentationEntityByEmail(String email);

    @Modifying
    @Transactional
    void deletePresentationEntityById(Integer id);
}