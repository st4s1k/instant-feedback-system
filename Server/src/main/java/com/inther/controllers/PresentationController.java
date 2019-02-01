package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.PresentationDto;
import com.inther.entities.implementation.PresentationEntity;
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
    public ResponseEntity<?> putPresentation(@Validated(value = {RequestDataValidator.PutPresentation.class}) @RequestBody PresentationDto presentationDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.putPresentation(modelMapper.map(presentationDtoToPut, PresentationEntity.class)));
    }

    @GetMapping
    public ResponseEntity<?> getPresentations(@RequestParam(value = "email", required = false) String email) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.getPresentation(email));
    }

    @PatchMapping
    public ResponseEntity<?> patchPresentation(@Validated(value = {RequestDataValidator.PatchPresentation.class}) @RequestBody PresentationDto presentationDtoToPatch) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.patchPresentation(modelMapper.map(presentationDtoToPatch, PresentationEntity.class)));
    }

    @DeleteMapping
    public ResponseEntity<?> deletePresentation(@RequestParam(value = "presentationId") Integer presentationId) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.deletePresentation(presentationId));
    }

    @Autowired
    public PresentationController(PresentationService presentationService, ModelMapper modelMapper)
    {
        this.presentationService = presentationService;
        this.modelMapper = modelMapper;
    }
}