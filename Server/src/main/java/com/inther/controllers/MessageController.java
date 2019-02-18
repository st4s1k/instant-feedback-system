package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.services.mappers.MessageMapper;
import com.inther.dto.MessageDto;
import com.inther.entities.Message;
import com.inther.repositories.PresentationRepository;
import com.inther.services.entity.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/messages")
public class MessageController
{
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private final HttpHeaders httpHeaders;
    private final PresentationRepository presentationRepository;

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<?> addMessage(
            @Validated(value = {RequestDataValidator.AddMessage.class})
            @RequestBody MessageDto messageDto)
    {
        int status = messageService.addMessage(messageMapper.toEntity(messageDto));
        String statusMessage = "Something happened in message service...";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        switch (status) {
            case  0:
                statusMessage = "No such presentationId.";
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case -1:
                statusMessage = "Presentation not started yet!";
                httpStatus = HttpStatus.FORBIDDEN;
                break;
            case  1:
                statusMessage = "Message successfully added!";
                httpStatus = HttpStatus.CREATED;
                break;
        }

        return new ResponseEntity<>(statusMessage, httpHeaders, httpStatus);
    }

    @GetMapping(params = "presentationId")
    public ResponseEntity<?> getMessagesByPresentation(
            @RequestParam(value = "presentationId") String id)
    {
        if (presentationRepository.findById(UUID.fromString(id)).isPresent()) {
            List<Message> msgList = messageService.fetchMessagesByPresentationId(UUID.fromString(id));
            List<MessageDto> msgDtoList = msgList.stream().map(messageMapper::toDto).collect(Collectors.toList());

            return new ResponseEntity<>(msgDtoList, httpHeaders, msgList.isEmpty()
                    ? HttpStatus.NO_CONTENT
                    : HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Presentation not found!", httpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping
    public ResponseEntity<?> editMessage(
            @Validated(value = {RequestDataValidator.UpdateMessage.class})
            @RequestBody MessageDto messageDtoToPatch)
    {
        return messageService.editMessage(messageMapper.toEntity(messageDtoToPatch))
                .map(messageEdited -> messageEdited
                        ? new ResponseEntity<>( "Message successfully edited!", httpHeaders, HttpStatus.OK)
                        : new ResponseEntity<>( "You don't have enough rights to do this!", httpHeaders, HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>("No such text!", httpHeaders, HttpStatus.NOT_FOUND));
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMessage(
            @Validated(value = {RequestDataValidator.DeleteMessage.class})
            @PathVariable String id)
    {
        return messageService.deleteMessage(UUID.fromString(id))
                .map(messageDeleted -> messageDeleted
                        ? new ResponseEntity<>( "Message successfully deleted!", httpHeaders, HttpStatus.ACCEPTED)
                        : new ResponseEntity<>( "You don't have enough rights to do this!", httpHeaders, HttpStatus.FORBIDDEN))
                .orElseGet(() -> new ResponseEntity<>("No such text!", httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public MessageController(MessageService messageService,
                             MessageMapper messageMapper,
                             HttpHeaders httpHeaders,
                             PresentationRepository presentationRepository)
    {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
        this.httpHeaders = httpHeaders;
        this.presentationRepository = presentationRepository;
    }
}