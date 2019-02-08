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

import java.text.ParseException;
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


    // wtf, going to get rid of this...
    private Boolean validatePresentationEntityDateAndTime(Presentation storedEntity, Presentation patchingEntity)
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

    // to be cleaned
    public ResponseEntity<?> addPresentation(PresentationDto presentationDto) throws Exception
    {
        Presentation presentationEntity = convertToEntity(presentationDto);

        Optional<Presentation> optionalUserEntity = presentationRepository.findPresentationByTitle(presentationEntity.getTitle());
        if (!optionalUserEntity.isPresent())
        {
            if (presentationEntity.getStartDate().before(presentationEntity.getEndDate()))
            {
                presentationRepository.save(serviceUtilityBean.setAuthenticatedEmailPropertyValue(presentationEntity));
                responseBean.setHeaders(httpHeaders);
                responseBean.setStatus(HttpStatus.CREATED);
                responseBean.setResponse("Presentation with title: '" + presentationEntity.getTitle() + "' successfully added");
            }
            else
            {
                throw new DateTimeException("Presentation start or end time is invalid");
            }
        }
        else
        {
            throw new DuplicatedEntryException("Presentation with title: '" + presentationEntity.getTitle() + "' already exists");
        }
        return new ResponseEntityWrapper<>(responseBean);
    }

    public ResponseEntity<?> getPresentationsFromDataBase()
    {
        List<PresentationDto> presentationDtoList = presentationRepository
                .findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return (presentationDtoList.isEmpty() ?
                new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    public ResponseEntity<?> getPresentation(UUID id)
    {
        return presentationRepository
                .findPresentationById(id)
                .map(this::convertToDto)
                .<ResponseEntity<?>>map(presentationDto -> new ResponseEntity<>(presentationDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    // to be cleaned
    public ResponseEntity<?> editPresentation(PresentationDto presentationDto) throws Exception
    {

        Presentation presentationEntity = convertToEntity(presentationDto);

        Optional<Presentation> optionalPresentationEntity = presentationRepository.findPresentationById(presentationEntity.getId());
        if (optionalPresentationEntity.isPresent())
        {
            if (authorityUtilityBean.getCurrentAuthenticationEmail().equals(optionalPresentationEntity.get().getEmail())
                    || authorityUtilityBean.validateAdminAuthority())
            {
                if (validatePresentationEntityDateAndTime(optionalPresentationEntity.get(), presentationEntity))
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

    // to be cleaned
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

    private PresentationDto convertToDto(Presentation presentation) {
        PresentationDto presentationDto = modelMapper.map(presentation, PresentationDto.class);
        presentationDto.setStartDate(presentation.getStartDate());
        presentationDto.setEndDate(presentation.getEndDate());
        return presentationDto;
    }

    private Presentation convertToEntity(PresentationDto presentationDto) throws ParseException {
        Presentation presentation = modelMapper.map(presentationDto, Presentation.class);
        presentation.setStartDate(presentationDto.getStartDateConverted());
        presentation.setEndDate(presentationDto.getEndDateConverted());
        return presentation;
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