package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.PresentationDto;
import com.inther.entities.PresentationEntity;
import com.inther.services.PresentationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/presentations")
public class PresentationController
{
    private final PresentationService presentationService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addPresentation(@Validated(value = {RequestDataValidator.PutPresentation.class}) @RequestBody PresentationDto presentationDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.addPresentation(modelMapper.map(presentationDtoToPut, PresentationEntity.class)));
    }

    @GetMapping(value = {"", "/{email}"})
    public ResponseEntity<?> getPresentations(@PathVariable(value = "email", required = false) String email) throws Exception
    {
        return presentationService.getPresentation(email);
    }

    @PutMapping
    public ResponseEntity<?> editPresentation(@Validated(value = {RequestDataValidator.PatchPresentation.class}) @RequestBody PresentationDto presentationDtoToPatch) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.editPresentation(modelMapper.map(presentationDtoToPatch, PresentationEntity.class)));
    }

    @DeleteMapping(value = {"", "/{id}"})
    public ResponseEntity<?> deletePresentation(@PathVariable(value = "id") UUID id) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.deletePresentation(id));
    }

    @Autowired
    public PresentationController(PresentationService presentationService, ModelMapper modelMapper)
    {
        this.presentationService = presentationService;
        this.modelMapper = modelMapper;
    }
}