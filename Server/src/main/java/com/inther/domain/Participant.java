package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participant
{
    @Id
    @GeneratedValue(generator = "personIdGenerator")
    @GenericGenerator(name = "personIdGenerator" , strategy = "increment")
    @Column(name = "participant_id")
    private int participantsId;


    public int getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(int participantsId) {
        this.participantsId = participantsId;
    }
}
