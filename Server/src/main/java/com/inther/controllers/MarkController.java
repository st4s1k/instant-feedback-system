package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.MarkDto;
import com.inther.entities.MarkEntity;
import com.inther.services.MarkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/mark")
public class MarkController
{
    private final MarkService markService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addMark(@Validated(value = {RequestDataValidator.PutMark.class}) @RequestBody MarkDto markDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(markService.addMark(modelMapper.map(markDtoToPut, MarkEntity.class)));
    }

    @Autowired
    public MarkController(MarkService markService, ModelMapper modelMapper)
    {
        this.markService = markService;
        this.modelMapper = modelMapper;
    }
}