package com.inther.mappers;

import com.inther.dto.MarkDto;
import com.inther.entities.Mark;
import com.inther.mappers.helpers.PresentationHelper;
import com.inther.mappers.helpers.UserHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = { PresentationHelper.class, UserHelper.class })
public interface MarkMapper {

    @Mapping(target = "userId", source = "mark.user.id")
    @Mapping(target = "presentationId", source = "mark.presentation.id")
    MarkDto toDto(Mark mark);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    Mark toEntity(MarkDto markDto);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    void updateMarkFromDto(MarkDto markDto, @MappingTarget Mark mark);

    @Mapping(target = "userId", source = "mark.user.id")
    @Mapping(target = "presentationId", source = "mark.presentation.id")
    void updateDtoFromMark(Mark mark, @MappingTarget MarkDto markDto);

    void patchDto(MarkDto source, @MappingTarget MarkDto destination);

    void patchEntity(Mark source, @MappingTarget Mark destination);

}
