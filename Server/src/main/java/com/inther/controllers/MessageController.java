package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.MessageDto;
import com.inther.entities.Message;
import com.inther.services.entity.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController
{
    private final MessageService messageService;
    private final ModelMapper modelMapper;
    private final HttpHeaders httpHeaders;

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<?> addMessage(
            @Validated(value = {RequestDataValidator.AddMessage.class})
            @RequestBody MessageDto messageDtoToPut)
    {
        return messageService.addMessage(modelMapper.map(messageDtoToPut, Message.class))
                .map(messageAdded -> messageAdded ?
                        new ResponseEntity<>( "Presentation not started yet!", httpHeaders, HttpStatus.FORBIDDEN) :
                        new ResponseEntity<>( "Message successfully added!", httpHeaders, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>("No such presentationId.", httpHeaders, HttpStatus.NOT_FOUND));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping
    public ResponseEntity<?> editMessage(
            @Validated(value = {RequestDataValidator.UpdateMessage.class})
            @RequestBody MessageDto messageDtoToPatch)
    {
        return messageService.editMessage(modelMapper.map(messageDtoToPatch, Message.class))
                .map(messageEdited -> messageEdited ?
                        new ResponseEntity<>( "You don't have enough rights to do this!", httpHeaders, HttpStatus.FORBIDDEN) :
                        new ResponseEntity<>( "Message successfully edited!", httpHeaders, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>("No such text!", httpHeaders, HttpStatus.NOT_FOUND));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id)
    {
        return messageService.deleteMessage(UUID.fromString(id))
                .map(messageDeleted -> messageDeleted ?
                        new ResponseEntity<>( "You don't have enough rights to do this!", httpHeaders, HttpStatus.FORBIDDEN) :
                        new ResponseEntity<>( "Message successfully deleted!", httpHeaders, HttpStatus.ACCEPTED))
                .orElseGet(() -> new ResponseEntity<>("No such text!", httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public MessageController(MessageService messageService,
                             ModelMapper modelMapper,
                             HttpHeaders httpHeaders)
    {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
        this.httpHeaders = httpHeaders;
    }
}