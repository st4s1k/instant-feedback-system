package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.ServiceUtilityBean;
import com.inther.entities.implementation.PresentationEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PresentationService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final PresentationRepository presentationRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean putPresentation(PresentationEntity presentationEntity) throws Exception
    {
        Optional<PresentationEntity> optionalUserEntity = presentationRepository.findPresentationEntityByPresentationTitle(presentationEntity.getPresentationTitle());
        if (!optionalUserEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(presentationEntity.getEmail()))
            {
                presentationRepository.save(presentationEntity);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Presentation with title " + presentationEntity.getPresentationTitle() + " successfully added");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new DuplicatedEntryException("Presentation with same title already exists");
        }
        return responseBean;
    }

    public ResponseBean getPresentation(String email) throws Exception
    {
        Optional<List<PresentationEntity>> optionalPresentationEntities = serviceUtilityBean.getPresentationsWithOrWithoutFilter(email);
        if (optionalPresentationEntities.isPresent())
        {
            responseBean.setHeaders(httpHeaders);
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse(optionalPresentationEntities.get());
        }
        else
        {
            throw new NotFoundEntryException("Presentations not found");
        }
        return responseBean;
    }

    public ResponseBean patchPresentation(PresentationEntity presentationEntity) throws Exception
    {
        Optional<PresentationEntity> optionalPresentationEntity = presentationRepository.findPresentationEntityByPresentationId(presentationEntity.getPresentationId());
        if (optionalPresentationEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalPresentationEntity.get().getEmail()) || authorityUtilityBean.validateAdminAuthority())
            {
                presentationRepository.save(serviceUtilityBean.patchEntity(optionalPresentationEntity.get(), presentationEntity));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("Presentation wih id " + presentationEntity.getPresentationId() + " successfully patched");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id " + presentationEntity.getPresentationId() + " not found");
        }
        return responseBean;
    }

    public ResponseBean deletePresentation(Integer presentationId) throws Exception
    {
        Optional<PresentationEntity> optionalPresentationEntity = presentationRepository.findPresentationEntityByPresentationId(presentationId);
        if (optionalPresentationEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalPresentationEntity.get().getEmail()) || authorityUtilityBean.validateAdminAuthority())
            {
                presentationRepository.deletePresentationEntityByPresentationId(presentationId);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("Presentation with id " + presentationId + " successfully deleted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id " + presentationId + " not found");
        }
        return responseBean;
    }

    @Autowired
    public PresentationService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean, PresentationRepository presentationRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.presentationRepository = presentationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}