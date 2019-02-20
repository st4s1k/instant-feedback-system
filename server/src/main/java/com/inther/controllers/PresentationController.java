package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.services.mappers.PresentationMapper;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.services.entity.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private final PresentationMapper presentationMapper;

    //    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<?> addPresentation(
            @Validated(value = {RequestDataValidator.AddPresentation.class})
            @RequestBody PresentationDto presentationDto)
    {
        Presentation presentation = presentationMapper.toEntity(presentationDto);

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

    @GetMapping(params = { "title_like", "page", "size" })
    public ResponseEntity<?> getPresentationsByTitle(
            @Validated(value = {RequestDataValidator.GetPresentationList.class})
            @RequestParam(value = "title_like") String keyword,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size)
    {
        Page<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsWithTitleKeyword(keyword, page, size)
                .map(presentationMapper::toDto);

        return presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(params = { "email_like", "page", "size" })
    public ResponseEntity<?> getPresentationsByEmailKeyword(
            @RequestParam(value = "email_like") String keyword,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size)
    {
        Page<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsByEmailKeyword(keyword, page, size)
                .map(presentationMapper::toDto);

        return presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(params = { "title_or_email_like", "page", "size" })
    public ResponseEntity<?> getPresentationsByTitleOrEmailKeyword(
            @RequestParam(value = "title_or_email_like") String keyword,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size)
    {
        Page<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsByTitleOrEmailKeyword(keyword, page, size)
                .map(presentationMapper::toDto);

        return presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(params = "email")
    public ResponseEntity<?> getPresentationsByEmail(
            @RequestParam(value = "email") String email)
    {
        List<PresentationDto> presentationDtoList = presentationService
                .searchForPresentationsByEmail(email).stream()
                .map(presentationMapper::toDto)
                .collect(Collectors.toList());

        return presentationDtoList.isEmpty()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(presentationDtoList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getFullPresentationList()
    {
        List<PresentationDto> presentationDtoList = presentationService
                .fetchAllPresentations().stream()
                .map(presentationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(presentationDtoList, httpHeaders,
                presentationDtoList.isEmpty()
                        ? HttpStatus.NO_CONTENT
                        : HttpStatus.OK);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<?> getPresentationListByPageAndSize(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) {
        Page<PresentationDto> presentationDtoPage = presentationService
                .fetchPresentationsByPageAndSize(page, size)
                .map(presentationMapper::toDto);

        return new ResponseEntity<>(presentationDtoPage, httpHeaders,
                presentationDtoPage.isEmpty()
                        ? HttpStatus.NO_CONTENT
                        : HttpStatus.OK);
    }

    @GetMapping(params = {"email","page", "size"})
    public ResponseEntity<?> getPresentationListByUserAndPageAndSize(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size) {
        Page<PresentationDto> presentationDtoPage = presentationService
                .fetchPresentationsByUserAndPageAndSize(email,page, size)
                .map(presentationMapper::toDto);

        return new ResponseEntity<>(presentationDtoPage, httpHeaders,
                presentationDtoPage.isEmpty()
                        ? HttpStatus.NO_CONTENT
                        : HttpStatus.OK);
    }



    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePresentation(
            @PathVariable(value = "id") String id)
    {
        return presentationService
                .deletePresentation(UUID.fromString(id))
                .map(deleted -> deleted
                        ? new ResponseEntity<>("Presentation successfully deleted.",
                        httpHeaders, HttpStatus.OK)
                        : new ResponseEntity<>("Unable to delete this presentation.",
                        httpHeaders, HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>("Presentation not found.",
                        httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public PresentationController(PresentationService presentationService,
                                  HttpHeaders httpHeaders,
                                  PresentationMapper presentationMapper)
    {
        this.presentationService = presentationService;
        this.httpHeaders = httpHeaders;
        this.presentationMapper = presentationMapper;
    }
}