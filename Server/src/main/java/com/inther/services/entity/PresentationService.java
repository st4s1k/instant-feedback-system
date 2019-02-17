package com.inther.services.entity;

import com.inther.entities.Participant;
import com.inther.entities.Presentation;
import com.inther.entities.User;
import com.inther.repositories.*;
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
    private final MessageRepository messageRepository;
    private final MarkRepository markRepository;
    private final MailSender mailSender;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository,
                               UserRepository userRepository,
                               ParticipantRepository participantRepository,
                               MessageRepository messageRepository,
                               MarkRepository markRepository,
                               MailSender mailSender)
    {
        this.presentationRepository = presentationRepository;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.messageRepository = messageRepository;
        this.markRepository = markRepository;
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

    public List<Presentation> searchForPresentationsByEmailKeyword(String keyword) {
        return presentationRepository.findPresentationsByUser_EmailIgnoreCaseContaining(keyword);
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
        if (optionalPresentation.isPresent()) {
//            sendNotificationMessages(optionalPresentation.get(),
//                    "Presentation was canceled",
//                    "Presentation '" + optionalPresentation.get().getTitle() + "' was canceled");

            // Delete all presentation marks
            markRepository.deleteMarksByPresentation_Id(id);
            // Delete all presentation messages
            messageRepository.deleteMessagesByPresentation_Id(id);
            // Delete all presentation participants
            participantRepository.deleteParticipantsByPresentation_Id(id);
            // Delete presentation
            presentationRepository.deletePresentationById(id);

            return Optional.of(!presentationRepository.existsById(id));
        } else {
            return Optional.empty();
        }
    }
}
