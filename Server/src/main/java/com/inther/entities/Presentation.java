package com.inther.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
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

    private String email;

    private String title;

    private String description;

    private Time startTime;

    private Time endTime;

    private Date date;

    private String place;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> marks;
}