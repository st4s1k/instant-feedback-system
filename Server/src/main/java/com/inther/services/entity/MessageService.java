package com.inther.services.entity;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.entities.Message;
import com.inther.repositories.MessageRepository;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final MessageRepository messageRepository;
    private final PresentationRepository presentationRepository;

    @Autowired
    public MessageService(AuthorityUtilityBean authorityUtilityBean,
                          PresentationRepository presentationRepository,
                          MessageRepository messageRepository)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.presentationRepository = presentationRepository;
        this.messageRepository = messageRepository;
    }

    public Optional<Boolean> addMessage(Message message)
    {
        return presentationRepository.findPresentationById(message.getPresentation().getId())
                .map(p ->
//                        LocalDateTime.now().isAfter(p.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
//                        &&
                                messageRepository.save(message).equals(message));
    }

    public List<Message> fetchMessagesByPresentationId(UUID presentationId) {
        return messageRepository.findMessagesByPresentation_Id(presentationId);
    }

    /**
     * Edits requested message entity.
     * <p>
     *     This will query {@link MessageRepository#findMessageById(UUID)} to obtain the
     *     message entity.
     * </p>
     *
     * @param message message entity to be edited
     * @return {@code Optional} edited state of the message
     */

    public Optional<Boolean> editMessage(Message message)
    {
        return messageRepository.findMessageById(message.getId())
                .map(msg -> messageRepository.save(message).equals(message));
    }

    public Optional<Boolean> deleteMessage(UUID id)
    {
        return messageRepository.findMessageById(id)
                .map(message -> {
                    messageRepository.deleteMessageById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}