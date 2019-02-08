package com.inther.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "presentations")
public class Presentation
{
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String place;

    @OneToMany(mappedBy = "presentationId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Participant> participants;
    @OneToMany(mappedBy = "presentationId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Message> messages;
    @OneToMany(mappedBy = "presentationId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Mark> marks;
}