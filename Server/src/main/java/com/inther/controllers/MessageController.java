package com.inther.controllers;

import com.inther.dto.MessageDto;
import com.inther.entities.MessageEntity;
import com.inther.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/message")
public class MessageController
{
    private final MessageService messageService;

    @PutMapping
    public ResponseEntity<Object> putMessage(@Valid @RequestBody MessageDto messageDtoToPut) throws Exception
    {
        return null;
    }

    @PatchMapping
    public ResponseEntity<Object> patchMessage(@Valid @RequestBody MessageEntity messageEntityToPatch) throws Exception
    {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteMessage(@RequestParam(value = "messageId") Integer messageId) throws Exception
    {
        return null;
    }

    @Autowired
    public MessageController(MessageService messageService)
    {
        this.messageService = messageService;
    }
}