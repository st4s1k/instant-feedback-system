package com.inther.mappers;

import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.mappers.helpers.PresentationHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = { PresentationHelper.class })
public interface ParticipantMapper {

    @Mapping(target = "presentationId", source = "participant.presentation.id")
    ParticipantDto toDto(Participant participant);

    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    Participant toEntity(ParticipantDto participantDto);

    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    void updateFromDto(ParticipantDto participantDto, @MappingTarget Participant participant);

    @Mapping(target = "presentationId", source = "participant.presentation.id")
    void updateFromEntity(Participant participant, @MappingTarget ParticipantDto participantDto);

    void patchDto(ParticipantDto source, @MappingTarget ParticipantDto destination);

    void patchEntity(Participant source, @MappingTarget Participant destination);
}
