package com.inther.services.mappers;

import com.inther.dto.MessageDto;
import com.inther.entities.Message;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper implements Mapper<Message, MessageDto> {

    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;

    public MessageMapper(PresentationRepository presentationRepository,
                         UserRepository userRepository) {
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message toEntity(MessageDto messageDto) {

        if (messageDto == null) { return null; }

        return Message.builder()
                .id(messageDto.getId())
                .presentation(messageDto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(messageDto.getPresentationId())
                        .orElse(null))
                .user(messageDto.getUserId() == null
                        ? null
                        : userRepository
                        .findUserById(messageDto.getUserId()).orElse(null))
                .text(messageDto.getText())
                .type(messageDto.getType())
                .anonymous(messageDto.getAnonymous())
                .build();
    }

    @Override
    public MessageDto toDto(Message entity) {
        return null;
    }

    @Override
    public void patchEntity(Message source, Message destination) {

    }

    @Override
    public void patchDto(MessageDto source, MessageDto destination) {

    }
}
