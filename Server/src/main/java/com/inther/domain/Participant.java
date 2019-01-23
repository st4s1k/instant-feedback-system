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

    @Column(name = "USERNAME")
    private Integer username;

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

    public Integer getUserId()
    {
        return username;
    }

    public void setUserId(Integer userId)
    {
        this.username = userId;
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