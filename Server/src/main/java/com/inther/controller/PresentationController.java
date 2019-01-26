package com.inther.controller;

import com.inther.domain.Presentation;
import com.inther.services.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/presentation")
public class PresentationController
{
    private final PresentationService presentationService;

    @PutMapping
    public Map<String, Object> addPresentation(@RequestBody Presentation presentationToAdd)
    {
        return null;
    }

    @GetMapping
    public Map<String, Object> getPresentations(@RequestParam(value = "filterByEmail") String email)
    {
        return null;
    }

    @PatchMapping
    public Map<String, Object> editPresentation(@RequestBody Presentation presentationToEdit)
    {
        return null;
    }

    @DeleteMapping
    public Map<String, Object> deleteSelPresentation(@RequestParam(value = "presentationId") Integer presentationId)
    {
        return null;
    }

    @Autowired
    public PresentationController(PresentationService presentationService)
    {
        this.presentationService = presentationService;
    }
}