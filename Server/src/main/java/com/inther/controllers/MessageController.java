package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.MessageDto;
import com.inther.entities.Message;
import com.inther.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/message")
public class MessageController
{
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addMessage(@Validated(value = {RequestDataValidator.PutMessage.class}) @RequestBody MessageDto messageDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.addMessage(modelMapper.map(messageDtoToPut, Message.class)));
    }

    @PutMapping
    public ResponseEntity<?> editMessage(@Validated(value = {RequestDataValidator.PatchMessage.class}) @RequestBody MessageDto messageDtoToPatch) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.editMessage(modelMapper.map(messageDtoToPatch, Message.class)));
    }

    @DeleteMapping(value = {"", "/{id}"})
    public ResponseEntity<?> deleteMessage(@PathVariable(value = "id") UUID id) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.deleteMessage(id));
    }

    @Autowired
    public MessageController(MessageService messageService, ModelMapper modelMapper)
    {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }
}