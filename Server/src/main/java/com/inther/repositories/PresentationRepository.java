package com.inther.repositories;

import com.inther.entities.Presentation;
import com.inther.entities.User;
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
    List<Presentation> findPresentationsByUser(User user);
    List<Presentation> findPresentationsByUser_Email(String userEmail);
    Optional<Presentation> findPresentationByTitle(String title);
    Optional<Presentation> findPresentationById(UUID id);
    // Filtered query
    List<Presentation> findPresentationsByTitleIgnoreCaseContaining(String keyword);
    List<Presentation> findPresentationsByUser_EmailIgnoreCaseContaining(String keyword);

    @Transactional
    void deletePresentationById(UUID id);

    @Transactional
    void deletePresentationsByUser_Id(UUID userId);
}