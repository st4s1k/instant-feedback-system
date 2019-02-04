package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.implementation.ParticipantEntity;
import com.inther.entities.implementation.PresentationEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.ParticipantRepository;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ParticipantService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final PresentationRepository presentationRepository;
    private final ParticipantRepository participantRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putParticipant(ParticipantEntity participantEntity) throws Exception
    {
        Optional<PresentationEntity> optionalPresentationEntity = presentationRepository
                .findPresentationEntityById(participantEntity.getPresentationId());
        if (optionalPresentationEntity.isPresent())
        {
            Optional<ParticipantEntity> optionalParticipantEntity = participantRepository
                    .findParticipantEntityByPresentationIdAndEmail(participantEntity.getPresentationId(), authorityUtilityBean.getCurrentAuthenticationEmail());
            if (!optionalParticipantEntity.isPresent())
            {
                participantRepository.save(serviceUtilityBean.setAuthenticatedEmailPropertyValue(participantEntity));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Your joined presentation with id: '" + participantEntity.getPresentationId() + "'");
            }
            else
            {
                throw new DuplicatedEntryException("You are already joined this presentation");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + participantEntity.getPresentationId() + "' not found");
        }
        return responseBean;
    }
    public ResponseBean deleteParticipant(Integer id) throws Exception
    {
        Optional<ParticipantEntity> optionalParticipantEntity = participantRepository.findParticipantEntityById(id);
        if (optionalParticipantEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalParticipantEntity.get().getEmail()) || authorityUtilityBean.validateAdminAuthority())
            {
                participantRepository.deleteParticipantEntityById(id);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("Participant with id: '" + id + "' successfully deleted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Participant with id: '" + id + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public ParticipantService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                              PresentationRepository presentationRepository, ParticipantRepository participantRepository,
                              ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.presentationRepository = presentationRepository;
        this.participantRepository = participantRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}