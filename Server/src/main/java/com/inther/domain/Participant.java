package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "PARTICIPANTS")
public class Participant
{
    @Id
    @GeneratedValue(generator = "participantIdGenerator")
    @GenericGenerator(name = "participantIdGenerator", strategy = "increment")
    @Column(name = "PARTICIPANT_ID")
    private Integer participantId;

    @Column(name = "EMAIL")
    private Integer email;

    @Column(name = "PRESENTATION_ID")
    private Integer presentationId;

    public Integer getParticipantId()
    {
        return participantId;
    }

    public void setParticipantId(Integer participantId)
    {
        this.participantId = participantId;
    }

    public Integer getEmail()
    {
        return email;
    }

    public void setEmail(Integer email)
    {
        this.email = email;
    }

    public Integer getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
}