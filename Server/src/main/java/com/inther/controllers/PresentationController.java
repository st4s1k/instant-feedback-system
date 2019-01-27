package com.inther.controllers;

import com.inther.dto.PresentationDto;
import com.inther.entities.PresentationEntity;
import com.inther.services.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/presentation")
public class PresentationController
{
    private final PresentationService presentationService;

    @PutMapping
    public ResponseEntity<Object> putPresentation(@Valid @RequestBody PresentationDto presentationDtoToPut) throws Exception
    {
        return null;
    }

    @GetMapping
    public ResponseEntity<Object> getPresentations(@Valid @RequestParam(value = "filterByEmail", required = false) String email) throws Exception
    {
        return null;
    }

    @PatchMapping
    public ResponseEntity<Object> patchPresentation(@Valid @RequestBody PresentationEntity presentationEntityToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePresentation(@Valid @RequestParam(value = "presentationId") Integer presentationId) throws Exception
    {
        return null;
    }

    @Autowired
    public PresentationController(PresentationService presentationService)
    {
        this.presentationService = presentationService;
    }
}