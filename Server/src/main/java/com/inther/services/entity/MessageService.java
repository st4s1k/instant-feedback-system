package com.inther.services.entity;

import com.inther.entities.Message;
import com.inther.entities.Presentation;
import com.inther.repositories.MessageRepository;
import com.inther.repositories.PresentationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class MessageService
{
    private final MessageRepository messageRepository;
    private final PresentationRepository presentationRepository;

    @Autowired
    public MessageService(PresentationRepository presentationRepository,
                          MessageRepository messageRepository)
    {
        this.presentationRepository = presentationRepository;
        this.messageRepository = messageRepository;
    }

    public int addMessage(Message message)
    {
        int status;

        Optional<Presentation> presentation = presentationRepository
                .findById(message.getPresentation().getId());

        if (!presentation.isPresent()) {
            status = 0;
        } else if (LocalDateTime.now().isBefore(ChronoLocalDateTime.from(LocalDateTime.of(
                presentation.get().getDate(),
                presentation.get().getStartTime()).atZone(ZoneId.systemDefault())))) {
            log.error("Presentation not started yet!");
            log.error("LocalDateTime.now(): {}", LocalDateTime.now().toString());
            log.error("presentation start date: {}", ChronoLocalDateTime.from(LocalDateTime.of(
                    presentation.get().getDate(),
                    presentation.get().getStartTime()).atZone(ZoneId.systemDefault())));
            status = -1;
        } else {
            messageRepository.save(message);
            status = 1;
        }

        return status;
    }

    public List<Message> fetchMessagesByPresentationId(UUID presentationId) {
        return messageRepository.findAllByPresentation_Id(presentationId);
    }

    /**
     * Edits requested message entity.
     * <p>
     *     This will query {@link MessageRepository#findById(Object)} to obtain the
     *     message entity.
     * </p>
     *
     * @param message message entity to be edited
     * @return {@code Optional} edited state of the message
     */

    public Optional<Boolean> editMessage(Message message)
    {
        return messageRepository.findById(message.getId())
                .map(msg -> messageRepository.save(message).equals(message));
    }

    public Optional<Boolean> deleteMessage(UUID id)
    {
        return messageRepository.findById(id)
                .map(message -> {
                    messageRepository.deleteById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}