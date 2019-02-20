package com.inther.services.mappers;

import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.repositories.PresentationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParticipantMapper implements Mapper<Participant, ParticipantDto> {

    private final PresentationRepository presentationRepository;

    public ParticipantMapper(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Override
    public Participant toEntity(ParticipantDto dto) {

        log.debug("Source DTO: {}", dto);

        if (dto == null) { return null; }

        Participant entity = Participant.builder().build();
        entity.setId(dto.getId());
        entity.setPresentation(dto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findById(dto.getPresentationId())
                        .orElse(null));
        entity.setEmail(dto.getEmail());

        log.debug("Result entity: {}", entity);


        return entity;
    }

    @Override
    public ParticipantDto toDto(Participant entity) {

        log.debug("Source entity: {}", entity);

        if (entity == null) { return null; }

        ParticipantDto dto = ParticipantDto.builder().build();
        dto.setId(entity.getId());
        dto.setPresentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId());
        dto.setEmail(entity.getEmail());

        log.debug("Result DTO: {}", dto);

        return dto;
    }

    @Override
    public void patchEntity(Participant source, Participant destination) {

        log.debug("Source entity: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

//        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setEmail(source.getEmail());

        log.debug("Result entity: {}", destination);
    }

    @Override
    public void patchDto(ParticipantDto source, ParticipantDto destination) {

        log.debug("Source DTO: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setEmail(source.getEmail());

        log.debug("Result DTO: {}", destination);
    }
}
