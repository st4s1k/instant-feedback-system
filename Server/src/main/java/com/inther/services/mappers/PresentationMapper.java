package com.inther.services.mappers;

import com.inther.dto.PresentationDto;
import com.inther.entities.Mark;
import com.inther.entities.Presentation;
import com.inther.repositories.MarkRepository;
import com.inther.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

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

        log.debug("Source DTO: {}", dto);

        if (dto == null) { return null; }

        Presentation entity = Presentation.builder().build();

        entity.setId(dto.getId());

        entity.setUser(dto.getEmail() == null ? null :
                userRepository.findByEmail(dto.getEmail()).orElse(null));

        entity.setTitle(dto.getTitle());

        entity.setDescription(dto.getDescription());

        entity.setStartTime(dto.getStartTime() == null ? null :
                LocalTime.parse(dto.getStartTime(), DateTimeFormatter.ISO_TIME));

        entity.setEndTime(dto.getEndTime() == null ? null :
                LocalTime.parse(dto.getEndTime(), DateTimeFormatter.ISO_TIME));

        entity.setDate(dto.getDate() == null ? null :
                LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_DATE));

        entity.setPlace(dto.getPlace());

        log.debug("Result entity: {}", entity);

        return entity;
    }

    @Override
    public PresentationDto toDto(Presentation entity) {

        log.debug("Source entity: {}", entity);

        if (entity == null) { return null; }

        PresentationDto dto = PresentationDto.builder().build();

        dto.setId(entity.getId());

        dto.setEmail(entity.getUser() == null ? null :
                entity.getUser().getEmail());

        dto.setTitle(entity.getTitle());

        dto.setDescription(entity.getDescription());

        dto.setStartTime(entity.getStartTime() == null ? null :
                entity.getStartTime().toString());

        dto.setEndTime(entity.getEndTime() == null ? null :
                entity.getEndTime().toString());

        dto.setDate(entity.getDate() == null ? null :
                entity.getDate().toString());

        dto.setPlace(entity.getPlace());

        getEntityMetadata(entity, dto);

        log.debug("Result dto: {}", dto);

        return dto;
    }

    @Override
    public void patchEntity(Presentation source, Presentation destination) {

        log.debug("Source entity: {}", source);

        if (source == null || destination == null || source.equals(destination)) { return; }

        destination.setId(source.getId());
        destination.setUser(source.getUser());
        destination.setTitle(source.getTitle());
        destination.setDescription(source.getDescription());
        destination.setStartTime(source.getStartTime());
        destination.setEndTime(source.getEndTime());
        destination.setDate(source.getDate());
        destination.setPlace(source.getPlace());

        log.debug("Result entity: {}", destination);
    }

    @Override
    public void patchDto(PresentationDto source, PresentationDto destination) {

        log.debug("Source DTO: {}", source);

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
        destination.setVoteCount(source.getVoteCount());

        log.debug("Result dto: {}", destination);
    }

    private void getEntityMetadata(Presentation entity, PresentationDto dto) {

        dto.setAvgMark(markRepository.findAllByPresentation_Id(entity.getId()).stream()
                .mapToDouble(Mark::getValue).average().orElse(0d));

        dto.setVoteCount(markRepository.findAllByPresentation_Id(entity.getId()).size());

        dto.setFinished(entity.getEndTime() != null && LocalDateTime.now()
                .isAfter(ChronoLocalDateTime.from(LocalDateTime.of(
                        entity.getDate(),
                        entity.getEndTime()).atZone(ZoneId.systemDefault()))));

        dto.setStarted(entity.getStartTime() != null && !dto.getFinished() && LocalDateTime.now()
                .isAfter(ChronoLocalDateTime.from(LocalDateTime.of(
                        entity.getDate(),
                        entity.getStartTime()).atZone(ZoneId.systemDefault()))));
    }
}
