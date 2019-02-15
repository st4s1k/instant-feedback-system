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
    public Participant toEntity(ParticipantDto participantDto) {

        if (participantDto == null) { return null; }

        return Participant.builder()
                .id(participantDto.getId())
                .presentation(participantDto.getPresentationId() == null
                        ? null
                        : presentationRepository
                        .findPresentationById(participantDto.getPresentationId())
                        .orElse(null))
                .email(participantDto.getEmail())
                .build();
    }

    @Override
    public ParticipantDto toDto(Participant participant) {

        if (participant == null) { return null; }

        return ParticipantDto.builder()
                .id(participant.getId())
                .presentationId(participant.getPresentation() == null
                        ? null
                        : participant.getPresentation().getId())
                .email(participant.getEmail())
                .build();
    }

    @Override
    public void patchEntity(Participant source, Participant destination) {

        if (source == null || destination == null) { return; }

        destination.setId(source.getId());
        destination.setPresentation(source.getPresentation());
        destination.setEmail(source.getEmail());
    }

    @Override
    public void patchDto(ParticipantDto source, ParticipantDto destination) {

        if (source == null || destination == null) { return; }

        destination.setId(source.getId());
        destination.setPresentationId(source.getPresentationId());
        destination.setEmail(source.getEmail());
    }
}
