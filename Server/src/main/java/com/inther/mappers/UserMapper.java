package com.inther.mappers;

import com.inther.dto.UserDto;
import com.inther.entities.User;
import com.inther.mappers.helpers.UserHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = UserHelper.class)
public interface UserMapper {

    @Mapping(target = "token", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    void updateFromDto(UserDto userDto, @MappingTarget User user);

    @Mapping(target = "token", ignore = true)
    void updateFromEntity(User user, @MappingTarget UserDto userDto);

    void patchDto(UserDto source, @MappingTarget UserDto destination);

    void patchEntity(User source, @MappingTarget User destination);
}
