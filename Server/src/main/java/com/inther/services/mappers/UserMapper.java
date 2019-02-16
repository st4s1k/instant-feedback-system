package com.inther.services.mappers;

import com.inther.dto.UserDto;
import com.inther.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserMapper implements Mapper<User, UserDto> {


    @Override
    public User toEntity(UserDto dto) {

        log.debug("Source DTO: {}", dto);

        if (dto == null) { return null; }

        User entity = User.builder().build();

        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());

        log.debug("Result entity: {}", entity);

        return entity;
    }

    @Override
    public UserDto toDto(User entity) {

        log.debug("Source entity: {}", entity);

        if (entity == null) { return null; }

        UserDto dto = UserDto.builder().build();

        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());

        log.debug("Result DTO: {}", dto);

        return dto;
    }

    @Override
    public void patchEntity(User source, User destination) {

        log.debug("Source entity: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setPassword(source.getPassword());
        destination.setRole(source.getRole());

        log.debug("Result entity: {}", destination);
    }

    @Override
    public void patchDto(UserDto source, UserDto destination) {

        log.debug("Source DTO: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setPassword(source.getPassword());
        destination.setRole(source.getRole());

        log.debug("Result DTO: {}", destination);
    }
}
