package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.entities.implementation.MarkEntity;
import com.inther.entities.implementation.PresentationEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.MarkRepository;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MarkService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final PresentationRepository presentationRepository;
    private final MarkRepository markRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putMark(MarkEntity markEntity) throws Exception
    {
        Optional<PresentationEntity> optionalPresentationEntity = presentationRepository
                .findPresentationEntityByPresentationId(markEntity.getPresentationId());
        if (!optionalPresentationEntity.isPresent())
        {
            Optional<MarkEntity> optionalMarkEntity = markRepository.findMarkEntityByPresentationIdAndEmail(markEntity.getPresentationId(), markEntity.getEmail());
            if (!optionalMarkEntity.isPresent())
            {
                if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(markEntity.getEmail()))
                {
                    markRepository.save(markEntity);
                    responseBean.setHeaders(httpHeaders);
                    responseBean.setStatus(HttpStatus.CREATED);
                    responseBean.setResponse("Your mark for presentation with id " + markEntity.getPresentationId() + " successfully added");
                }
                else
                {
                    throw new AccessDeniedException("Access denied for you authority");
                }
            }
            else
            {
                throw new DuplicatedEntryException("You have already rated this presentation");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + markEntity.getPresentationId() + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public MarkService(AuthorityUtilityBean authorityUtilityBean, PresentationRepository presentationRepository,
                       MarkRepository markRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.presentationRepository = presentationRepository;
        this.markRepository = markRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}