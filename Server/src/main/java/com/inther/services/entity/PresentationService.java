package com.inther.services.entity;

import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import com.inther.entities.User;
import com.inther.repositories.ParticipantRepository;
import com.inther.repositories.PresentationRepository;
import com.inther.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PresentationService
{
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final ModelMapper modelMapper;
    private final MailSender mailSender;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository,
                               UserRepository userRepository,
                               ParticipantRepository participantRepository, ModelMapper modelMapper,
                               MailSender mailSender)
    {
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.modelMapper = modelMapper;
        this.mailSender = mailSender;
    }

    private void sendNotificationMessages(Presentation presentation, String subject, String text)
    {



        for (Participant participant : participantRepository.findParticipantsByPresentation_Id(presentation.getId()))
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(participant.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
            mailSender.send(simpleMailMessage);
        }
    }

    public Presentation newPresentation(Presentation presentation)
    {
        return presentationRepository.save(presentation);
    }

    public List<Presentation> fetchAllPresentations()
    {
        return presentationRepository.findAll();
    }

    public List<Presentation> searchForPresentationsWithTitle(String title) {
        return presentationRepository.findPresentationsByTitleIgnoreCaseContaining(title);
    }

    public List<Presentation> searchForPresentationsByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent()
                ? presentationRepository.findPresentationsByUser(user.get())
                : new ArrayList<>();
    }

    public Optional<Presentation> searchForRequestedPresentation(UUID id)
    {
        return presentationRepository.findPresentationById(id);
    }

    public Optional<Boolean> editPresentation(Presentation presentation)
    {
        return presentationRepository.findPresentationById(presentation.getId())
                .map(p -> {
                    presentationRepository.save(presentation);
                    return true;
                });
    }

    public Optional<Boolean> deletePresentation(UUID id)
    {
        Optional<Presentation> optionalPresentation = presentationRepository.findPresentationById(id);
        optionalPresentation.ifPresent(presentation -> sendNotificationMessages(presentation, "Presentation was canceled",
                "Presentation '" + presentation.getTitle() + "' was canceled"));
        return presentationRepository.findPresentationById(id)
                .map(p -> {
                    presentationRepository.deletePresentationById(id);
                    return !presentationRepository.existsById(id);
                });
    }
}
