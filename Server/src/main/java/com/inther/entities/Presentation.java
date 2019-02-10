package com.inther.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "presentations")
@Entity
public class Presentation
{
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    private UUID userID;

    private String email;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private String place;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks;

    public Presentation setTitle(String title) {
        this.title = title;
        return this;
    }

    public Presentation setDescription(String description) {
        this.description = description;
        return this;
    }

    public Presentation setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Presentation setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Presentation setPlace(String place) {
        this.place = place;
        return this;
    }

    public Presentation setParticipants(List<Participant> participants) {
        this.participants = participants;
        return this;
    }

    public Presentation setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Presentation setMarks(List<Mark> marks) {
        this.marks = marks;
        return this;
    }

    public Presentation updateBy(Presentation presentation) {
        this.email = presentation.email;
        this.title = presentation.title;
        this.description = presentation.description;
        this.startDate = presentation.startDate;
        this.endDate = presentation.endDate;
        this.place = presentation.place;
        return this;
    }
}