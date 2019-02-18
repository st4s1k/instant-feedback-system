package com.inther.repositories;

import com.inther.entities.Presentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, UUID>
{
    // Exact query
    List<Presentation> findAllByUser_Email(String userEmail);
    // Filtered query
    Page<Presentation> findAllByTitleIgnoreCaseContaining(String keyword, Pageable pageable);
    Page<Presentation> findAllByUser_EmailIgnoreCaseContaining(String keyword, Pageable pageable);
    Page<Presentation> findAllByTitleOrUser_EmailIgnoreCaseContaining(String title, String userEmail, Pageable pageable);

    //Page
    Page<Presentation> findAllByUser_Email(String email, Pageable pageable);

    @Transactional
    void deleteAllByUser_Id(UUID userId);
}