package com.inther.services.mappers;

import com.inther.dto.PresentationDto;
import com.inther.entities.Mark;
import com.inther.entities.Presentation;
import com.inther.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PresentationMapper implements Mapper<Presentation, PresentationDto> {

    private final UserRepository userRepository;

    public PresentationMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Presentation toEntity(PresentationDto dto) {

        if (dto == null) { return null; }

        Presentation entity = Presentation.builder().build();
        entity.setId(dto.getId());
        entity.setUser(dto.getEmail() == null
                ? null
                : userRepository.findUserByEmail(dto.getEmail()).orElse(null));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartTime(dto.getStartTime() == null
                ? null
                : Date.valueOf(dto.getStartTime()));
        entity.setEndTime(dto.getEndTime() == null
                ? null
                : Date.valueOf(dto.getEndTime()));
        entity.setDate(dto.getDate() == null
                ? null
                : Date.valueOf(dto.getDate()));
        entity.setPlace(dto.getPlace());

        return entity;
    }

    @Override
    public PresentationDto toDto(Presentation entity) {

        if (entity == null) { return null; }

        PresentationDto dto = PresentationDto.builder().build();
        dto.setId(entity.getId());
        dto.setEmail(entity.getUser() == null
                ? null
                : entity.getUser().getEmail());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStartTime(entity.getStartTime() == null
                ? null
                : entity.getStartTime().toString()); // maybe some formatting needed
        dto.setEndTime(entity.getEndTime() == null
                ? null
                : entity.getEndTime().toString()); // maybe some formatting needed
        dto.setDate(entity.getDate() == null
                ? null
                : entity.getDate().toString()); // maybe some formatting needed
        dto.setPlace(entity.getPlace());
        dto.setAvgMark(entity.getMarks() == null || entity.getMarks().isEmpty()
                ? 0d
                : entity.getMarks().stream().mapToDouble(Mark::getValue).average().orElse(0d));

        return dto;
    }

    @Override
    public void patchEntity(Presentation source, Presentation destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setUser(source.getUser());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setStartTime(source.getStartTime());
        destination.setEndTime(source.getEndTime());
        destination.setDate(source.getDate());
        destination.setPlace(source.getPlace());
        destination.setParticipants(source.getParticipants());
        destination.setMessages(source.getMessages());
        destination.setMarks(source.getMarks());
    }

    @Override
    public void patchDto(PresentationDto source, PresentationDto destination) {

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setEmail(source.getEmail());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setStartTime(source.getStartTime());
        destination.setEndTime(source.getEndTime());
        destination.setDate(source.getDate());
        destination.setPlace(source.getPlace());
        destination.setAvgMark(source.getAvgMark());
    }
}
