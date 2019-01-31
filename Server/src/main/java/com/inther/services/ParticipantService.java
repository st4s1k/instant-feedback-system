package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.implementation.ParticipantEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ParticipantService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ParticipantRepository participantRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putParticipant(ParticipantEntity participantEntity) throws Exception
    {
        Optional<ParticipantEntity> optionalParticipantEntity = participantRepository
                .findParticipantEntityByPresentationIdAndEmail(participantEntity.getPresentationId(), participantEntity.getEmail());
        if (!optionalParticipantEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(participantEntity.getEmail()))
            {
                participantRepository.save(participantEntity);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Your joined presentation with id " + participantEntity.getPresentationId());
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new DuplicatedEntryException("You are already joined this presentation");
        }
        return responseBean;
    }

    public ResponseBean deleteParticipant(Integer participantId) throws Exception
    {
        Optional<ParticipantEntity> optionalParticipantEntity = participantRepository.findParticipantEntityByParticipantId(participantId);
        if (optionalParticipantEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalParticipantEntity.get().getEmail()) || authorityUtilityBean.validateAdminAuthority())
            {
                participantRepository.deleteParticipantEntityByParticipantId(participantId);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("Participant with id " + participantId + " successfully deleted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Participant with id " + participantId + " not found");
        }
        return responseBean;
    }

    @Autowired
    public ParticipantService(AuthorityUtilityBean authorityUtilityBean, ParticipantRepository participantRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.participantRepository = participantRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}