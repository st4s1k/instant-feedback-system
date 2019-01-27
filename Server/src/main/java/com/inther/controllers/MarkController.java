package com.inther.controllers;

import com.inther.dto.MarkDto;
import com.inther.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/mark")
public class MarkController
{
    private final MarkService markService;

    @PutMapping
    public ResponseEntity<Object> putMark(@Valid @RequestBody MarkDto markDtoToPut) throws Exception
    {
        return null;
    }

    @Autowired
    public MarkController(MarkService markService)
    {
        this.markService = markService;
    }
}