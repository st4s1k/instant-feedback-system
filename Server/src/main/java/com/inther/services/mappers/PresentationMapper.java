package com.inther.services.mappers;

import com.inther.dto.PresentationDto;
import com.inther.entities.Mark;
import com.inther.entities.Presentation;
import com.inther.repositories.MarkRepository;
import com.inther.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class PresentationMapper implements Mapper<Presentation, PresentationDto> {

    private final UserRepository userRepository;
    private final MarkRepository markRepository;

    public PresentationMapper(UserRepository userRepository,
                              MarkRepository markRepository) {
        this.userRepository = userRepository;
        this.markRepository = markRepository;
    }

    @Override
    public Presentation toEntity(PresentationDto dto) {

        if (dto == null) { return null; }

        Presentation entity = Presentation.builder().build();

        entity.setId(dto.getId());
        log.debug("entity.setId(" + dto.getId() + ") = " + entity.getId());

        entity.setUser(dto.getEmail() == null
                ? null
                : userRepository.findUserByEmail(dto.getEmail()).orElse(null));
        log.debug("entity.setUser(" + dto.getEmail() + ") = " + entity.getUser());

        entity.setTitle(dto.getTitle());
        log.debug("entity.setTitle(" + dto.getTitle() + ") = " + entity.getTitle());

        entity.setDescription(dto.getDescription());
        log.debug("entity.setDescription(" + dto.getDescription() + ") = " + entity.getDescription());

        entity.setStartTime(dto.getStartTime() == null
                ? null
                : LocalTime.parse(dto.getStartTime(), DateTimeFormatter.ISO_TIME));
        log.debug("entity.setStartTime(" + dto.getStartTime() + ") = " + entity.getStartTime());

        entity.setEndTime(dto.getEndTime() == null
                ? null
                : LocalTime.parse(dto.getEndTime(), DateTimeFormatter.ISO_TIME));
        log.debug("entity.setEndTime(" + dto.getEndTime() + ") = " + entity.getEndTime());

        entity.setDate(dto.getDate() == null
                ? null
                : LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_DATE));
        log.debug("entity.setDate(" + dto.getDate() + ") = " + entity.getDate());

        entity.setPlace(dto.getPlace());
        log.debug("entity.setPlace(" + dto.getPlace() + ") = " + entity.getPlace());

        log.debug("Result entity: {}", entity);

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

        dto.setAvgMark(markRepository
                .findMarksByPresentation_Id(entity.getId()).stream()
                .mapToDouble(Mark::getValue).average().orElse(0d));

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
