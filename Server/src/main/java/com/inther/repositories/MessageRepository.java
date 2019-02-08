package com.inther.repositories;

import com.inther.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, UUID>
{
    Optional<MessageEntity> findMessageEntityById(UUID id);

    @Transactional
    void deleteMessageEntityById(UUID id);
}