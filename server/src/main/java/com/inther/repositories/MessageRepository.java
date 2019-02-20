package com.inther.repositories;

import com.inther.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>
{
    List<Message> findAllByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteAllByPresentation_Id(UUID presentationId);

    @Transactional
    void deleteAllByUser_Id(UUID userId);
}