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
    List<Message> findMessagesByUserId(UUID user_id);
    List<Message> findMessageByAnonymousTrue();
    Optional<Message> findMessageById(UUID id);
    Optional<Message> findMessageByIdAndUserId(UUID id, UUID user_id);

    @Transactional
    void deleteMessageById(UUID id);
}