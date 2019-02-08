package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.PresentationDto;
import com.inther.entities.Presentation;
import com.inther.services.PresentationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/presentations")
public class PresentationController
{
    private final PresentationService presentationService;

    @PostMapping
    public ResponseEntity<?> addPresentation(
            @Validated(value = {RequestDataValidator.postPresentation.class})
            @RequestBody PresentationDto presentationDto) throws Exception
    {
        return presentationService.addPresentation(presentationDto);
    }

    @GetMapping
    public ResponseEntity<?> getPresentations()
    {
        return presentationService.getPresentationsFromDataBase();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPresentations(@PathVariable(value = "id") String id)
    {
        return presentationService.getPresentation(UUID.fromString(id));
    }

    @PutMapping
    public ResponseEntity<?> editPresentation(
            @Validated(value = {RequestDataValidator.updatePresentation.class})
            @RequestBody PresentationDto presentationDto) throws Exception
    {
        return presentationService.editPresentation(presentationDto);
    }

    @DeleteMapping(value = {"", "/{id}"})
    public ResponseEntity<?> deletePresentation(@PathVariable(value = "id") String id) throws Exception
    {
        return new ResponseEntityWrapper<>(presentationService.deletePresentation(UUID.fromString(id)));
    }

    @Autowired
    public PresentationController(PresentationService presentationService)
    {
        this.presentationService = presentationService;
    }
}