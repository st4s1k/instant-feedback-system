package com.inther.services.mappers;

import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.repositories.PresentationRepository;
import org.springframework.stereotype.Service;

@Service
public class ParticipantMapper implements Mapper<Participant, ParticipantDto> {

    private final PresentationRepository presentationRepository;

    public ParticipantMapper(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Override
    public Participant toEntity(ParticipantDto dto) {

        if (dto == null) { return null; }

        return Participant.builder()
                .id(dto.getId())
                .presentation(dto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(dto.getPresentationId())
                        .orElse(null))
                .email(dto.getEmail())
                .build();
    }

    @Override
    public ParticipantDto toDto(Participant entity) {

        if (entity == null) { return null; }

        return ParticipantDto.builder()
                .id(entity.getId())
                .presentationId(entity.getPresentation() == null
                        ? null
                        : entity.getPresentation().getId())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public void patchEntity(Participant source, Participant destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setEmail(source.getEmail());
    }

    @Override
    public void patchDto(ParticipantDto source, ParticipantDto destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setEmail(source.getEmail());
    }
}
