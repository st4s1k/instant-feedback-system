package com.inther.mappers.helpers;

import com.inther.entities.Presentation;
import com.inther.repositories.PresentationRepository;
import org.mapstruct.Named;

import java.util.UUID;

public class PresentationHelper {

    private final PresentationRepository presentationRepository;

    public PresentationHelper(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Named("presentationIdToEntity")
    public Presentation presentationIdToEntity(UUID presentationId) {
        return presentationRepository.findPresentationById(presentationId).orElse(null);
    }
}
