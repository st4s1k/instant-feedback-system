package com.inther.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participants")
public class Participant
{
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    @ManyToOne(targetEntity = Presentation.class)
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID presentationId;

    private String email;

    public Participant setPresentationId(UUID presentationId) {
        this.presentationId = presentationId;
        return this;
    }

    public Participant setEmail(String email) {
        this.email = email;
        return this;
    }

    public Participant updateBy(Participant participant) {
        this.presentationId = participant.presentationId;
        this.email = participant.email;
        return this;
    }
}