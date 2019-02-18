package com.inther.services.mappers;

import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.debug("Source DTO: {}", dto);

        if (dto == null) { return null; }

        Mark entity = Mark.builder().build();
        entity.setId(dto.getId());
        entity.setPresentation(dto.getPresentationId() == null
                ? null
                : presentationRepository
                .findById(dto.getPresentationId())
                .orElse(null));
        entity.setUser(dto.getUserId() == null
                ? null
                : userRepository
                .findById(dto.getUserId())
                .orElse(null));
        entity.setValue(dto.getValue());

        log.debug("Result entity: {}", entity);

        return entity;
    }

    @Override
    public MarkDto toDto(Mark entity) {

        log.debug("Source entity: {}", entity);

        if (entity == null) { return null; }

        MarkDto dto = MarkDto.builder().build();
        dto.setId(entity.getId());
        dto.setPresentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId());
        dto.setUserId(entity.getUser() == null
                        ? null
                        : entity.getUser().getId());
        dto.setValue(entity.getValue());

        log.debug("Result DTO: {}", dto);

        return dto;
    }

    @Override
    public void patchEntity(Mark source, Mark destination) {

        log.debug("Source entity: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setUser(source.getUser());
        destination.setValue(source.getValue());

        log.debug("Result entity: {}", destination);
    }

    @Override
    public void patchDto(MarkDto source, MarkDto destination) {

        log.debug("Source DTO: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setUserId(source.getUserId());
        destination.setValue(source.getValue());

        log.debug("Result DTO: {}", destination);
    }

}
