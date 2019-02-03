package com.inther.entities.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inther.entities.Entities;
import javax.persistence.*;

@Entity
@Table(name = "participants")
public class ParticipantEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Integer participantId;

    @JsonIgnore
    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private String email;

    public Integer getParticipantId()
    {
        return participantId;
    }
    public void setParticipantId(Integer participantId)
    {
        this.participantId = participantId;
    }
    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
}