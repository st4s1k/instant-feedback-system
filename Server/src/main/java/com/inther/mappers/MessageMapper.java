package com.inther.mappers;

import com.inther.dto.MessageDto;
import com.inther.dto.UserDto;
import com.inther.entities.Message;
import com.inther.entities.User;
import com.inther.mappers.helpers.PresentationHelper;
import com.inther.mappers.helpers.UserHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = { PresentationHelper.class, UserHelper.class })
public interface MessageMapper {

    @Mapping(target = "userId", source = "message.user.id")
    @Mapping(target = "presentationId", source = "message.presentation.id")
    @Mapping(target = "email", source = "message.user.email")
    MessageDto toDto(Message message);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    Message toEntity(MessageDto messageDto);

    @Mapping(target = "user", qualifiedByName = "userIdToEntity")
    @Mapping(target = "presentation", qualifiedByName = "presentationIdToEntity")
    void updateMessageFromDto(MessageDto messageDto, @MappingTarget Message message);

    @Mapping(target = "userId", source = "message.user.id")
    @Mapping(target = "presentationId", source = "message.presentation.id")
    @Mapping(target = "email", source = "message.user.email")
    void updateDtoFromMessage(Message message, @MappingTarget MessageDto messageDto);

    void patchDto(MessageDto source, @MappingTarget MessageDto destination);

    void patchEntity(Message source, @MappingTarget Message destination);
}
