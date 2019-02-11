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
            @Validated(value = {RequestDataValidator.AddPresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        return presentationService
                .createPresentationAttempt(modelMapper.map(presentationDto, Presentation.class)) ?
                new ResponseEntity<>(httpHeaders, HttpStatus.CREATED) :
                new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @PutMapping
    public ResponseEntity<?> editPresentation(
            @Validated(value = {RequestDataValidator.UpdatePresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        return presentationService
                .editPresentation(modelMapper.map(presentationDto, Presentation.class))
                .map(edited -> new ResponseEntity<>(httpHeaders, edited ? HttpStatus.ACCEPTED : HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPresentation(@PathVariable String id)
    {
        return presentationService
                .searchForRequestedPresentation(UUID.fromString(id))
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .map(presentationDto -> new ResponseEntity<>(presentationDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "title_like")
    public ResponseEntity<?> getPresentationsByTitle(
            @RequestParam(value = "title_like") String title) {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsWithTitle(title).stream()
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());

        return (presentationDtoList.isEmpty() ?
                new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<?> getPresentationsByEmail(
            @RequestParam String userId) {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsByUserId(UUID.fromString(userId)).stream()
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());

        return (presentationDtoList.isEmpty() ?
                new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> getFullPresentationList()
    {
        List<PresentationDto> presentationDtoList = presentationService
                .fetchAllPresentations().stream()
                .map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(presentationDtoList, httpHeaders,
                        presentationDtoList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePresentation(@PathVariable(value = "id") String id)
    {
        return presentationService
                .deletePresentation(UUID.fromString(id))
                .map(deleted -> new ResponseEntity<>(httpHeaders, deleted ? HttpStatus.ACCEPTED : HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }
}