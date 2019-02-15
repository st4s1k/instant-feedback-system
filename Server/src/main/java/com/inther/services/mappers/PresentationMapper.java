package com.inther.services.mappers;

import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import org.springframework.stereotype.Service;

@Service
public class PresentationMapper implements Mapper<Presentation, PresentationDto> {
    @Override
    public Presentation toEntity(PresentationDto dto) {
        return null;
    }

    @Override
    public PresentationDto toDto(Presentation entity) {
        return null;
    }

    @Override
    public void patchEntity(Presentation source, Presentation destination) {

    }

    @Override
    public void patchDto(PresentationDto source, PresentationDto destination) {

    }
}
