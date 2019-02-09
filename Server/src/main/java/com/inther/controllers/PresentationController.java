package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.services.PresentationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/presentations")
public class PresentationController
{
    private final PresentationService presentationService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;

    @Autowired
    public PresentationController(PresentationService presentationService,
                                  ModelMapper modelMapper,
                                  HttpHeaders httpHeaders)
    {
        this.presentationService = presentationService;
        this.modelMapper = modelMapper;
        this.httpHeaders = httpHeaders;
    }

    @PostMapping
    public ResponseEntity<?> addPresentation(
            @Validated(value = {RequestDataValidator.postPresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        return presentationService
                .createPresentationAttempt(modelMapper.map(presentationDto, Presentation.class))
                .map(presentation -> new ResponseEntity<>(httpHeaders, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT));
    }

    @GetMapping // Adding some criteria? Spring repository methods support filtering.
    public ResponseEntity<?> getPresentationList()
    {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForRequestedPresentationsList().stream()
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());

        return (presentationDtoList.isEmpty() ?
                new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPresentation(@PathVariable(value = "id") String id)
    {
        return presentationService
                .searchForRequestedPresentation(UUID.fromString(id))
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .map(presentationDto -> new ResponseEntity<>(presentationDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<?> editPresentation(
            @Validated(value = {RequestDataValidator.updatePresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        return presentationService
                .editPresentation(modelMapper.map(presentationDto, Presentation.class))
                .map(edited -> new ResponseEntity<>(httpHeaders, edited ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePresentation(@PathVariable(value = "id") String id)
    {
        return presentationService
                .deletePresentation(UUID.fromString(id))
                .map(deleted -> new ResponseEntity<>(httpHeaders, deleted ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }
}