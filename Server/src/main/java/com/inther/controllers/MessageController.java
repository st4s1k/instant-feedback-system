package com.inther.controllers;

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
    public ResponseEntity<?> putMessage(@Validated @RequestBody MessageEntity messageEntityToPut) throws Exception
    {
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> patchMessage(@Validated @RequestBody MessageEntity messageEntityToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMessage(@RequestParam(value = "messageId") Integer messageId) throws Exception
    {
        return null;
    }

    @Autowired
    public MessageController(MessageService messageService, ModelMapper modelMapper)
    {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }
}