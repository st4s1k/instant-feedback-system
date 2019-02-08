package com.inther.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "presentations")
public class PresentationEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String email;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String place;

    @OneToMany(targetEntity = ParticipantEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParticipantEntity> participants;
    @OneToMany(targetEntity = MessageEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MessageEntity> messages;
    @OneToMany(targetEntity = MarkEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MarkEntity> marks;
}