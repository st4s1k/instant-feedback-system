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
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID userID;
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

    public Presentation setEmail(String email) {
        this.email = email;
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