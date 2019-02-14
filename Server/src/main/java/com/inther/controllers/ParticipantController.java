package com.inther.controllers;

import com.inther.assets.validators.RequestDataValidator;
import com.inther.dto.ParticipantDto;
import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import com.inther.repositories.PresentationRepository;
import com.inther.services.entity.ParticipantService;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;
    private final MailSender mailSender;
    private final PresentationRepository presentationRepository;

    @GetMapping(value = "/{id}")
    public List<ParticipantDto> getPresentationParticipants(@PathVariable String id) {
        return participantService
                .fetchPresentationParticipants(UUID.fromString(id))
                .stream()
                .map(participant -> modelMapper.map(participant, ParticipantDto.class))
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
        Participant newParticipant = modelMapper.map(participantDtoToPut, Participant.class);
        Optional<UUID> newParticipantId = participantService.addParticipant(newParticipant);


        for (ParticipantDto participantDto : participantDtoToPut)
        {
            Optional<Presentation> optionalPresentation = presentationRepository.findPresentationById(participantDto.getPresentationId());
            optionalPresentation.ifPresent(presentation ->
                sendNotificationMessages(participantDto.getEmail(), "You has been invited on presentation",
                         "Presentation name: " + presentation.getTitle()
                            + "n/Presentation description: " + presentation.getDescription()
                            + "n/n/Presentation start time: " + presentation.getStartTime()
                            + "n/n/Presentation end time: " + presentation.getStartTime()
                            + "n/n/Presentation place: " + presentation.getPlace())
            );
        }


        return newParticipantId.isPresent()
                ? new ResponseEntity<>(httpHeaders, HttpStatus.CREATED)
                : new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteParticipant(@PathVariable String id)
    {
        Optional<Boolean> participantRemovalStatus = participantService.deleteParticipant(UUID.fromString(id));

        return participantRemovalStatus.map(deleted -> deleted
                ? new ResponseEntity<>(httpHeaders, HttpStatus.OK)
                : new ResponseEntity<>(httpHeaders, HttpStatus.CONFLICT))
                .orElseGet(() -> new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND));
    }

    @Autowired
    public ParticipantController(HttpHeaders httpHeaders, ParticipantService participantService, ModelMapper modelMapper, MailSender mailSender, PresentationRepository presentationRepository)
    {
        this.httpHeaders = httpHeaders;
        this.participantService = participantService;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
        this.presentationRepository = presentationRepository;
    }
}