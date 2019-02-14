package com.inther.repositories;

import com.inther.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>
{
    List<Message> findMessagesByUser_Id(UUID userId);
    List<Message> findMessageByAnonymousTrue();
    List<Message> findMessagesByPresentation_Id(UUID presentationId);
    Optional<Message> findMessageById(UUID id);
    Optional<Message> findMessageByIdAndUser_Id(UUID id, UUID userId);

    @Transactional
    void deleteMessageById(UUID id);
}