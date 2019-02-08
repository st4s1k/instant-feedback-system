package com.inther.repositories;

import com.inther.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>
{
    Optional<Message> findMessageEntityById(UUID id);

    @Transactional
    void deleteMessageEntityById(UUID id);
}