package com.inther.services;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.entities.implementation.PresentationEntity;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.DateTimeException;
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

    private Optional<List<PresentationEntity>> getPresentationsWithOrWithoutFilter(String email)
    {
        if (email != null)
        {
            return presentationRepository.findPresentationEntityByEmail(email);
        }
        else
        {
            return Optional.of(presentationRepository.findAll());
        }
    }
    private Boolean validatePresentationEntityDateAndTime(PresentationEntity storedEntity, PresentationEntity patchingEntity)
    {
        if ((patchingEntity.getPresentationStartDate() != null) && (patchingEntity.getPresentationEndDate() != null))
        {
            return patchingEntity.getPresentationStartDate().before(patchingEntity.getPresentationEndDate());
        }
        else if ((patchingEntity.getPresentationStartDate() != null) && (patchingEntity.getPresentationEndDate() == null))
        {
            return patchingEntity.getPresentationStartDate().before(storedEntity.getPresentationEndDate());
        }
        else if ((patchingEntity.getPresentationStartDate() == null) && (patchingEntity.getPresentationEndDate() != null))
        {
            return storedEntity.getPresentationStartDate().before(patchingEntity.getPresentationEndDate());
        }
        else
        {
            return true;
        }
    }

    public ResponseBean putPresentation(PresentationEntity presentationEntity) throws Exception
    {
        Optional<PresentationEntity> optionalUserEntity = presentationRepository.findPresentationEntityByPresentationTitle(presentationEntity.getPresentationTitle());
        if (!optionalUserEntity.isPresent())
        {
            if (presentationEntity.getPresentationStartDate().before(presentationEntity.getPresentationEndDate()))
            {
                presentationRepository.save(serviceUtilityBean.setAuthenticatedEmailPropertyValue(presentationEntity));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Presentation with title: '" + presentationEntity.getPresentationTitle() + "' successfully added");
            }
            else
            {
                throw new DateTimeException("Presentation start or end time is invalid");
            }
        }
        else
        {
            throw new DuplicatedEntryException("Presentation with title: '" + presentationEntity.getPresentationTitle() + "' already exists");
        }
        return responseBean;
    }
    public ResponseBean getPresentation(String email) throws Exception
    {
        Optional<List<PresentationEntity>> optionalPresentationEntities = getPresentationsWithOrWithoutFilter(email);
        if (optionalPresentationEntities.isPresent() && optionalPresentationEntities.get().size() > 0)
        {
            responseBean.setHeaders(httpHeaders);
            responseBean.setStatus(HttpStatus.OK);
            responseBean.setResponse(optionalPresentationEntities.get());
        }
        else
        {
            throw new NotFoundEntryException("No presentation was found for the given criteria");
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
                if (validatePresentationEntityDateAndTime(optionalPresentationEntity.get(), presentationEntity))
                {
                    presentationRepository.save(serviceUtilityBean.patchEntity(optionalPresentationEntity.get(), presentationEntity));
                    responseBean.setHeaders(httpHeaders);
                    responseBean.setStatus(HttpStatus.OK);
                    responseBean.setResponse("Presentation with id: '" + presentationEntity.getPresentationId() + "' successfully patched");
                }
                else
                {
                    throw new DateTimeException("Presentation start or end time is invalid");
                }
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + presentationEntity.getPresentationId() + "' not found");
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
                responseBean.setResponse("Presentation with id: '" + presentationId + "' successfully deleted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you authority");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + presentationId + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public PresentationService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                               PresentationRepository presentationRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.presentationRepository = presentationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}