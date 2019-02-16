package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.services.mappers.ParticipantMapper;
import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import com.inther.repositories.PresentationRepository;
import com.inther.services.entity.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/participants")
public class ParticipantController
{
    private final HttpHeaders httpHeaders;
    private final ParticipantService participantService;
    private final ParticipantMapper participantMapper;
    private final MailSender mailSender;
    private final PresentationRepository presentationRepository;

    @GetMapping(value = "/{id}")
    public List<ParticipantDto> getPresentationParticipants(@PathVariable String id) {
        return participantService
                .fetchPresentationParticipants(UUID.fromString(id))
                .stream()
                .map(participantMapper::toDto)
                .collect(Collectors.toList());
    }

    private void sendNotificationMessages(String to, String subject, String text)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }

    @PutMapping
    public ResponseEntity<?> putParticipant(
            @Validated(value = {RequestDataValidator.AddParticipant.class})
            @RequestBody List<ParticipantDto> participantDtoToPut)
    {
        List<Participant> newParticipantList = participantDtoToPut
                .stream()
                .map(participantMapper::toEntity)
                .collect(Collectors.toList());

        for (Participant newParticipant : newParticipantList)
        {
            participantService.addParticipant(newParticipant);

            Optional<Presentation> optionalPresentation = presentationRepository
                    .findPresentationById(newParticipant.getPresentation().getId());

            optionalPresentation.ifPresent(presentation ->
                sendNotificationMessages(newParticipant.getEmail(), "You have been invited to a presentation",
                         "Presentation name: " + presentation.getTitle()
                            + "n/Presentation description: " + presentation.getDescription()
                            + "n/n/Presentation start time: " + presentation.getStartTime()
                            + "n/n/Presentation end time: " + presentation.getStartTime()
                            + "n/n/Presentation place: " + presentation.getPlace())
            );
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable String id)
    {
        Optional<Boolean> participantRemovalStatus = participantService.deleteParticipant(UUID.fromString(id));

        return participantRemovalStatus.map(deleted -> deleted
                ? new ResponseEntity<>("Participant successfully deleted.", httpHeaders, HttpStatus.OK)
                : new ResponseEntity<>("Unable to delete participant", httpHeaders, HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>("Participant not found.", httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public ParticipantController(HttpHeaders httpHeaders,
                                 ParticipantService participantService,
                                 ParticipantMapper participantMapper,
                                 MailSender mailSender,
                                 PresentationRepository presentationRepository)
    {
        this.httpHeaders = httpHeaders;
        this.participantService = participantService;
        this.participantMapper = participantMapper;
        this.mailSender = mailSender;
        this.presentationRepository = presentationRepository;
    }
}