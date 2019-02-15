package com.inther.services.mappers;

import com.inther.dto.UserDto;
import com.inther.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDto> {


    @Override
    public User toEntity(UserDto dto) {

        if (dto == null) { return null; }

        User entity = User.builder().build();

        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());

        return entity;
    }

    @Override
    public UserDto toDto(User entity) {

        if (entity == null) { return null; }

        UserDto dto = UserDto.builder().build();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        return dto;
    }

    @Override
    public void patchEntity(User source, User destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setPassword(source.getPassword());
        destination.setRole(source.getRole());

    }

    @Override
    public void patchDto(UserDto source, UserDto destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setPassword(source.getPassword());
        destination.setRole(source.getRole());
    }
}
