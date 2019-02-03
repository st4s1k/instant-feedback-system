package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.dto.MessageDto;
import com.inther.entities.implementation.MessageEntity;
import com.inther.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController
{
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> putMessage(@Validated(value = {RequestDataValidator.PutMessage.class}) @RequestBody MessageDto messageDtoToPut) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.putMessage(modelMapper.map(messageDtoToPut, MessageEntity.class)));
    }

    @PatchMapping
    public ResponseEntity<?> patchMessage(@Validated(value = {RequestDataValidator.PatchMessage.class}) @RequestBody MessageDto messageDtoToPatch) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.patchMessage(modelMapper.map(messageDtoToPatch, MessageEntity.class)));
    }

    @DeleteMapping(value = {"", "/{messageId}"})
    public ResponseEntity<?> deleteMessage(@PathVariable(value = "messageId") Integer messageId) throws Exception
    {
        return new ResponseEntityWrapper<>(messageService.deleteMessage(messageId));
    }

    @Autowired
    public MessageController(MessageService messageService, ModelMapper modelMapper)
    {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }
}