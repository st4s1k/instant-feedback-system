package com.inther.services.mappers;

import com.inther.dto.UserDto;
import com.inther.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User toEntity(UserDto dto) {
        return null;
    }

    @Override
    public UserDto toDto(User entity) {
        return null;
    }

    @Override
    public void patchEntity(User source, User destination) {

    }

    @Override
    public void patchDto(UserDto source, UserDto destination) {

    }
}
