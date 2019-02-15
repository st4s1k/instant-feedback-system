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
    public Mark toEntity(MarkDto markDto) {

        if (markDto == null) { return null; }

        return Mark.builder()
                .id(markDto.getId())
                .presentation(markDto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(markDto.getPresentationId())
                        .orElse(null))
                .user(markDto.getUserId() == null
                        ? null
                        : userRepository
                        .findUserById(markDto.getUserId())
                        .orElse(null))
                .value(markDto.getValue())
                .build();
    }

    @Override
    public MarkDto toDto(Mark mark) {

        if (mark == null) { return null; }

        return MarkDto.builder()
                .id(mark.getId())
                .presentationId(mark.getPresentation() == null
                        ? null
                        : mark.getPresentation().getId())
                .userId(mark.getUser() == null
                        ? null
                        : mark.getUser().getId())
                .value(mark.getValue())
                .build();
    }

    @Override
    public void patchEntity(Mark source, Mark destination) {

        if (source == null || destination == null) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setUser(source.getUser());
        destination.setValue(source.getValue());
    }

    @Override
    public void patchDto(MarkDto source, MarkDto destination) {

        if (source == null || destination == null) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setUserId(source.getUserId());
        destination.setValue(source.getValue());
    }

}
