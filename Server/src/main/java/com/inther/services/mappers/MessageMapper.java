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

        Message entity = Message.builder().build();
        entity.setId(dto.getId());
        entity.setPresentation(dto.getPresentationId() == null
                ? null
                : presentationRepository
                .findPresentationById(dto.getPresentationId())
                .orElse(null));
        entity.setUser(dto.getUserId() == null
                ? null
                : userRepository
                .findUserById(dto.getUserId()).orElse(null));
        entity.setText(dto.getText());
        entity.setType(dto.getType());
        // I would like to mention,
        // that using lombok, it handles Boolean objects with "get" method prefix,
        // unlike boolean primitives which are handled with "is" method prefix.
        entity.setAnonymous(dto.getAnonymous());

        return entity;
    }

    @Override
    public MessageDto toDto(Message entity) {

        if (entity == null) { return null; }

        MessageDto dto = MessageDto.builder().build();
        dto.setId(entity.getId());
        dto.setPresentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId());
        dto.setUserId(entity.getUser() == null
                        ? null
                        : entity.getUser().getId());
        dto.setEmail(entity.getUser() == null
                        ? null
                        : entity.getUser().getEmail());
        dto.setText(entity.getText());
        dto.setType(entity.getType());
        dto.setAnonymous(entity.getAnonymous());

        return dto;
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
