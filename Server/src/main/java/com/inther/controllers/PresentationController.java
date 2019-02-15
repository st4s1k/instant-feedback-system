package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.mappers.PresentationMapperImpl;
import com.inther.repositories.UserRepository;
import com.inther.services.entity.PresentationService;
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
    private final HttpHeaders httpHeaders;
    private final PresentationMapperImpl presentationMapper;
    private final UserRepository userRepository;

    //    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> addPresentation(
            @Validated(value = {RequestDataValidator.AddPresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        Presentation presentation = presentationMapper.toEntity(presentationDto);

//        return new ResponseEntity<>(presentation, httpHeaders, HttpStatus.TEMPORARY_REDIRECT);

        return presentationService
                .newPresentation(presentation).equals(presentation)
                ? new ResponseEntity<>(presentation.getId(), httpHeaders, HttpStatus.CREATED)
                : new ResponseEntity<>(httpHeaders, HttpStatus.EXPECTATION_FAILED);
    }

    //    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<?> editPresentation(
            @Validated(value = {RequestDataValidator.UpdatePresentation.class})
            @RequestBody PresentationDto presentationDto)
    {


        Presentation presentation = presentationMapper.toEntity(presentationDto);
        return presentationService
                .editPresentation(presentation)
                .map(edited -> new ResponseEntity<>(presentation.getId(), httpHeaders, edited
                        ? HttpStatus.ACCEPTED
                        : HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPresentation(@PathVariable String id)
    {
        return presentationService
                .searchForRequestedPresentation(UUID.fromString(id))
                .map(presentationMapper::toDto)
                .map(presentationDto -> new ResponseEntity<>(presentationDto, httpHeaders, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @GetMapping(params = "title_like")
    public ResponseEntity<?> getPresentationsByTitle(
            @Validated(value = {RequestDataValidator.GetPresentationList.class})
            @RequestParam(value = "title_like") String title) {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsWithTitle(title).stream()
                .map(presentationMapper::toDto)
                .collect(Collectors.toList());

        return (presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    @GetMapping(params = "email")
    public ResponseEntity<?> getPresentationsByEmail(@RequestParam String email) {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsByEmail(email).stream()
                .map(presentationMapper::toDto)
                .collect(Collectors.toList());

        return (presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> getFullPresentationList()
    {
        List<PresentationDto> presentationDtoList = presentationService
                .fetchAllPresentations().stream()
                .map(presentationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(presentationDtoList, httpHeaders,
                presentationDtoList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePresentation(@PathVariable(value = "id") String id)
    {
        return presentationService
                .deletePresentation(UUID.fromString(id))
                .map(deleted -> new ResponseEntity<>(httpHeaders, deleted ? HttpStatus.ACCEPTED : HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public PresentationController(PresentationService presentationService,
                                  HttpHeaders httpHeaders,
                                  PresentationMapperImpl presentationMapper,
                                  UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.presentationService = presentationService;
        this.httpHeaders = httpHeaders;
        this.presentationMapper = presentationMapper;
    }
}