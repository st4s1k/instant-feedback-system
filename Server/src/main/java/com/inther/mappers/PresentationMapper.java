package com.inther.mappers;

import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.mappers.helpers.MarkHelper;
import com.inther.mappers.helpers.PresentationHelper;
import com.inther.mappers.helpers.UserHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = { PresentationHelper.class, UserHelper.class, MarkHelper.class })
public interface PresentationMapper {


    @Mapping(target = "email", source = "presentation.user.email")
    @Mapping(target = "avgMark", qualifiedByName = "calculateAverageMark")
    @Mapping(target = "startTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "endTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    PresentationDto toDto(Presentation presentation);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "marks", ignore = true)
    @Mapping(target = "startTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "endTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "date", dateFormat = "yyyy-MM-dd")
    Presentation toEntity(PresentationDto presentationDto);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "participants", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "marks", ignore = true)
    void updateFromDto(PresentationDto presentationDto, @MappingTarget Presentation presentation);

    @Mapping(target = "email", source = "presentation.user.email")
    @Mapping(target = "avgMark", qualifiedByName = "calculateAverageMark")
    void updateFromEntity(Presentation presentation, @MappingTarget PresentationDto presentationDto);

    void patchDto(PresentationDto source, @MappingTarget PresentationDto destination);

    void patchEntity(Presentation source, @MappingTarget Presentation destination);
}
