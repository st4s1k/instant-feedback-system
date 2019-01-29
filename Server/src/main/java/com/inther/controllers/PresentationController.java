package com.inther.controllers;

import com.inther.entities.PresentationEntity;
import com.inther.services.PresentationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/presentation")
public class PresentationController
{
    private final PresentationService presentationService;
    private final ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> putPresentation(@Validated @RequestBody PresentationEntity presentationEntityToPut) throws Exception
    {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getPresentations(@RequestParam(value = "filterByEmail", required = false) String email) throws Exception
    {
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> patchPresentation(@Validated @RequestBody PresentationEntity presentationEntityToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deletePresentation(@RequestParam(value = "presentationId") Integer presentationId) throws Exception
    {
        return null;
    }

    @Autowired
    public PresentationController(PresentationService presentationService, ModelMapper modelMapper)
    {
        this.presentationService = presentationService;
        this.modelMapper = modelMapper;
    }
}