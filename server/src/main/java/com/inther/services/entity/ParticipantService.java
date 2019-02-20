package com.inther.services.entity;

import com.inther.beans.utilities.AuthorityUtilityBean;
import com.inther.entities.Participant;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ParticipantRepository participantRepository;
    private final MailSender mailSender;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository,
                              AuthorityUtilityBean authorityUtilityBean,
                              MailSender mailSender)
    {
        this.participantRepository = participantRepository;
        this.authorityUtilityBean = authorityUtilityBean;
        this.mailSender = mailSender;
    }

    public void addParticipant(Participant participant)
    {
        Optional<Participant> similarParticipant = participantRepository
                .findByPresentation_IdAndEmail(participant.getPresentation().getId(), participant.getEmail());

        if (!similarParticipant.isPresent()) {
            participantRepository.save(participant);
        }
    }

    public List<Participant> fetchPresentationParticipants(UUID presentationId) {
        return participantRepository.findAllByPresentation_Id(presentationId);
    }

    public Optional<Boolean> deleteParticipant(UUID id)
    {
        return participantRepository.findById(id)
                .filter(p -> authorityUtilityBean.getCurrentUserId().equals(id)
                        || authorityUtilityBean.validateAdminAuthority())
                .map(p -> {
                    participantRepository.deleteById(id);
                    return !participantRepository.existsById(id);
                });
    }

    public void sendNotificationMessages(String to, String subject, String text)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }
}