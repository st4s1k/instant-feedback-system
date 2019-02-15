package com.inther.services.mappers;

import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MarkMapper implements Mapper<Mark, MarkDto> {

    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;

    public MarkMapper(PresentationRepository presentationRepository,
                      UserRepository userRepository) {
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Mark toEntity(MarkDto dto) {

        if (dto == null) { return null; }

        return Mark.builder()
                .id(dto.getId())
                .presentation(dto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(dto.getPresentationId())
                        .orElse(null))
                .user(dto.getUserId() == null
                        ? null
                        : userRepository
                        .findUserById(dto.getUserId())
                        .orElse(null))
                .value(dto.getValue())
                .build();
    }

    @Override
    public MarkDto toDto(Mark entity) {

        if (entity == null) { return null; }

        return MarkDto.builder()
                .id(entity.getId())
                .presentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId())
                .userId(entity.getUser() == null
                        ? null
                        : entity.getUser().getId())
                .value(entity.getValue())
                .build();
    }

    @Override
    public void patchEntity(Mark source, Mark destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setUser(source.getUser());
        destination.setValue(source.getValue());
    }

    @Override
    public void patchDto(MarkDto source, MarkDto destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setUserId(source.getUserId());
        destination.setValue(source.getValue());
    }

}
