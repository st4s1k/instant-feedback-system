package com.inther.services.entity;

import com.inther.entities.Presentation;
import com.inther.entities.User;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PresentationService
{
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository,
                               UserRepository userRepository,
                               ModelMapper modelMapper)
    {
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Presentation newPresentation(Presentation presentation)
    {
        Presentation newPresentation = presentationRepository.save(presentation);
        if (newPresentation.getParticipants() != null && !newPresentation.getParticipants().isEmpty()) {
            newPresentation.getParticipants().forEach(
                    participant -> participant.setPresentationId(presentation.getId()));
        }
        return newPresentation;
    }

    public List<Presentation> fetchAllPresentations()
    {
        return presentationRepository.findAll();
    }

    public List<Presentation> searchForPresentationsWithTitle(String title) {
        return presentationRepository.findPresentationsByTitleIgnoreCaseContaining(title);
    }

    public List<Presentation> searchForPresentationsByUserId(UUID userId) {
        Optional<User> user = userRepository.findUserById(userId);
        return user.isPresent()
                ? presentationRepository.findPresentationsByEmail(user.get().getEmail())
                : new ArrayList<>();
    }

    public Optional<Presentation> searchForRequestedPresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id);
    }

    public Optional<Boolean> editPresentation(Presentation presentation)
    {
        return presentationRepository.findPresentationById(presentation.getId())
                .map(p -> {
                    modelMapper.map(p, presentation);
                    return presentationRepository.save(p).equals(presentation);
                });
    }

    public Optional<Boolean> deletePresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id)
                .map(p -> {
                    presentationRepository.deletePresentationById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}
