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
    public Message toEntity(MessageDto dto) {

        if (dto == null) { return null; }

        return Message.builder()
                .id(dto.getId())
                .presentation(dto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(dto.getPresentationId())
                        .orElse(null))
                .user(dto.getUserId() == null
                        ? null
                        : userRepository
                        .findUserById(dto.getUserId()).orElse(null))
                .text(dto.getText())
                .type(dto.getType())
                // I would like to mention,
                // that using lombok, it handles Boolean objects with "get" method prefix,
                // unlike boolean primitives which are handled with "is" method prefix.
                .anonymous(dto.getAnonymous())
                .build();
    }

    @Override
    public MessageDto toDto(Message entity) {

        if (entity == null) { return null; }

        return MessageDto.builder()
                .id(entity.getId())
                .presentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId())
                .userId(entity.getUser() == null
                        ? null
                        : entity.getUser().getId())
                .email(entity.getUser() == null
                        ? null
                        : entity.getUser().getEmail())
                .text(entity.getText())
                .type(entity.getType())
                .anonymous(entity.getAnonymous())
                .build();
    }

    @Override
    public void patchEntity(Message source, Message destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setUser(source.getUser());
        destination.setText(source.getText());
        destination.setType(source.getType());
        destination.setAnonymous(source.getAnonymous());
    }

    @Override
    public void patchDto(MessageDto source, MessageDto destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setUserId(source.getUserId());
        destination.setEmail(source.getEmail());
        destination.setText(source.getText());
        destination.setType(source.getType());
        destination.setAnonymous(source.getAnonymous());
    }
}
