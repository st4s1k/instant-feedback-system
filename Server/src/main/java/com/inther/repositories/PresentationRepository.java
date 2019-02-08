package com.inther.repositories;

import com.inther.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, UUID>
{
    // Exact query
    Optional<List<Presentation>> findPresentationByEmail(String email);
    Optional<Presentation> findPresentationByTitle(String title);
    Optional<Presentation> findPresentationById(UUID id);
    // Filtered query
    Optional<List<Presentation>> findPresentationByEmailIgnoreCaseContaining(String keyWord);
    Optional<List<Presentation>> findPresentationByTitleIgnoreCaseContaining(String keyWord);
    Optional<List<Presentation>> findPresentationByTitleAndEmailAllIgnoreCaseContaining(String titlekeyWord, String emailKeyWord);

    @Transactional
    void deletePresentationEntityById(UUID id);
}