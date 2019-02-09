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

    public Optional<Presentation> createPresentationAttempt(Presentation presentation)
    {
        return presentationRepository
                .findPresentationByTitle(presentation.getTitle())
                .filter(p -> p.getStartDate().before(p.getEndDate()))
                .map(p -> p.setEmail(authorityUtilityBean.getCurrentAuthenticationEmail()))
                .map(presentationRepository::save);
    }

    public List<Presentation> searchForRequestedPresentationsList()
    {
        return presentationRepository.findAll();
    }

    public Optional<Presentation> searchForRequestedPresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id);
    }

    public Optional<Boolean> editPresentation(Presentation presentation)
    {
        return presentationRepository.findPresentationById(presentation.getId())
                .filter(p -> (authorityUtilityBean.getCurrentAuthenticationEmail().equals(p.getEmail())
                        || authorityUtilityBean.validateAdminAuthority())
                        && p.getStartDate() != null && p.getEndDate() != null)
                .map(p -> presentationRepository.exists(Example.of(p.updateBy(presentation))));
    }

    public Optional<Boolean> deletePresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id)
                .filter(p -> authorityUtilityBean.getCurrentAuthenticationEmail().equals(p.getEmail())
                        || authorityUtilityBean.validateAdminAuthority())
                .map(p -> {
                    presentationRepository.deletePresentationById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}
