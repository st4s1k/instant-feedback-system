package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.Mark;
import com.inther.entities.Presentation;
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
    private final ServiceUtilityBean serviceUtilityBean;
    private final PresentationRepository presentationRepository;
    private final MarkRepository markRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean addMark(Mark mark) throws Exception
    {
        Optional<Presentation> optionalPresentationEntity = presentationRepository
                .findPresentationById(mark.getPresentationId());
        if (optionalPresentationEntity.isPresent())
        {
            Optional<Mark> optionalMarkEntity = markRepository.findMarkEntityByPresentationIdAndEmail(mark.getPresentationId(),
                    authorityUtilityBean.getCurrentAuthenticationEmail());
            if (!optionalMarkEntity.isPresent())
            {
                markRepository.save(serviceUtilityBean.setAuthenticatedEmailPropertyValue(mark));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Your mark for presentation with id: '" + mark.getPresentationId() + "' successfully added");
            }
            else
            {
                throw new DuplicatedEntryException("You have already rated this presentation");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + mark.getPresentationId() + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public MarkService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                       PresentationRepository presentationRepository, MarkRepository markRepository,
                       ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.presentationRepository = presentationRepository;
        this.markRepository = markRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}