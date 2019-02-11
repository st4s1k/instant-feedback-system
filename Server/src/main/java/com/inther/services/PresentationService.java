package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.entities.Presentation;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PresentationService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final PresentationRepository presentationRepository;

    @Autowired
    public PresentationService(AuthorityUtilityBean authorityUtilityBean,
                               PresentationRepository presentationRepository)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.presentationRepository = presentationRepository;
    }

    public boolean createPresentationAttempt(Presentation presentation)
    {
        return !presentation.getStartDate().before(presentation.getEndDate())
                && presentationRepository.save(presentation).equals(presentation);
    }

    public List<Presentation> fetchAllPresentations()
    {
        return presentationRepository.findAll();
    }

    public List<Presentation> searchForPresentationsWithTitle(String title) {
        return presentationRepository.findPresentationsByTitleIgnoreCaseContaining(title);
    }

    public List<Presentation> searchForPresentationsByUserId(UUID userId) {
        return presentationRepository.findPresentationsByUser_Id(userId);
    }

    public Optional<Presentation> searchForRequestedPresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id);
    }

    public Optional<Boolean> editPresentation(Presentation presentation)
    {
        return presentationRepository.findPresentationById(presentation.getId())
                .filter(p -> (authorityUtilityBean.getCurrentUserEmail().equals(p.getUser().getEmail())
                        || authorityUtilityBean.validateAdminAuthority()))
                .map(p -> presentationRepository.exists(Example.of(p.updateBy(presentation))));
    }

    public Optional<Boolean> deletePresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id)
                .filter(p -> authorityUtilityBean.getCurrentUserEmail().equals(p.getUser().getEmail())
                        || authorityUtilityBean.validateAdminAuthority())
                .map(p -> {
                    presentationRepository.deletePresentationById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}
