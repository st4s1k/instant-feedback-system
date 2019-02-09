package com.inther.services;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import com.inther.beans.utilities.ServiceUtilityBean;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.exceptions.AccessDeniedException;
import com.inther.exceptions.DuplicatedEntryException;
import com.inther.exceptions.NotFoundEntryException;
import com.inther.repositories.PresentationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PresentationService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ServiceUtilityBean serviceUtilityBean;
    private final PresentationRepository presentationRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;
    private final ModelMapper modelMapper;


    private Boolean validatePresentationDateAndTime(Presentation storedEntity, Presentation patchingEntity)
    {
        if ((patchingEntity.getStartDate() != null) && (patchingEntity.getEndDate() != null))
        {
            return patchingEntity.getStartDate().before(patchingEntity.getEndDate());
        }
        else if ((patchingEntity.getStartDate() != null) && (patchingEntity.getEndDate() == null))
        {
            return patchingEntity.getStartDate().before(storedEntity.getEndDate());
        }
        else if ((patchingEntity.getStartDate() == null) && (patchingEntity.getEndDate() != null))
        {
            return storedEntity.getStartDate().before(patchingEntity.getEndDate());
        }
        else
        {
            return true;
        }
    }

    public ResponseEntity<?> addPresentation(PresentationDto presentationDto) throws Exception
    {
        Presentation presentation = modelMapper.map(presentationDto, Presentation.class);

        Optional<Presentation> optionalUserEntity = presentationRepository.findPresentationByTitle(presentation.getTitle());
        if (!optionalUserEntity.isPresent())
        {
            if (presentation.getStartDate().before(presentation.getEndDate()))
            {
                presentationRepository.save(serviceUtilityBean.setAuthenticatedEmailPropertyValue(presentation));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Presentation with title: '" + presentation.getTitle() + "' successfully added");
            }
            else
            {
                throw new DateTimeException("Presentation start or end time is invalid");
            }
        }
        else
        {
            throw new DuplicatedEntryException("Presentation with title: '" + presentation.getTitle() + "' already exists");
        }
        return new ResponseEntityWrapper<>(responseBean);
    }

    public ResponseEntity<?> getPresentationsFromDataBase()
    {
        List<PresentationDto> presentationDtoList = presentationRepository
                .findAll().stream()
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());
        return (presentationDtoList.isEmpty() ?
                new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    public ResponseEntity<?> getPresentation(UUID id)
    {
        return presentationRepository
                .findPresentationById(id)
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .<ResponseEntity<?>>map(presentationDto -> new ResponseEntity<>(presentationDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> editPresentation(PresentationDto presentationDto) throws Exception
    {

        Presentation presentationEntity = modelMapper.map(presentationDto, Presentation.class);

        Optional<Presentation> optionalPresentationEntity = presentationRepository.findPresentationById(presentationEntity.getId());
        if (optionalPresentationEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalPresentationEntity.get().getEmail())
                    || authorityUtilityBean.validateAdminAuthority())
            {
                if (validatePresentationDateAndTime(optionalPresentationEntity.get(), presentationEntity))
                {
                    presentationRepository.save(serviceUtilityBean.patchEntity(optionalPresentationEntity.get(), presentationEntity));
                    responseBean.setHeaders(httpHeaders);
                    responseBean.setStatus(HttpStatus.OK);
                    responseBean.setResponse("Presentation with id: '" + presentationEntity.getId() + "' successfully patched");
                }
                else
                {
                    throw new DateTimeException("Presentation start or end time is invalid");
                }
            }
            else
            {
                throw new AccessDeniedException("Access denied for you name");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + presentationEntity.getId() + "' not found");
        }
        return new ResponseEntityWrapper<>(responseBean);
    }

    public ResponseBean deletePresentation(UUID id) throws Exception
    {
        Optional<Presentation> optionalPresentationEntity = presentationRepository.findPresentationById(id);
        if (optionalPresentationEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalPresentationEntity.get().getEmail())
                    || authorityUtilityBean.validateAdminAuthority())
            {
                presentationRepository.deletePresentationEntityById(id);
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.OK);
                responseBean.setResponse("Presentation with id: '" + id + "' successfully deleted");
            }
            else
            {
                throw new AccessDeniedException("Access denied for you name");
            }
        }
        else
        {
            throw new NotFoundEntryException("Presentation with id: '" + id + "' not found");
        }
        return responseBean;
    }

    @Autowired
    public PresentationService(AuthorityUtilityBean authorityUtilityBean, ServiceUtilityBean serviceUtilityBean,
                               PresentationRepository presentationRepository,
                               ResponseBean responseBean, HttpHeaders httpHeaders, ModelMapper modelMapper)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.serviceUtilityBean = serviceUtilityBean;
        this.presentationRepository = presentationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
        this.modelMapper = modelMapper;
    }
}
