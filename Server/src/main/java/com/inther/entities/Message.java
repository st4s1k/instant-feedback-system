package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "messages")
public class Message
{
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(targetEntity = Presentation.class, fetch = FetchType.LAZY)
    @Type(type = "pg-uuid")
    private UUID presentationId;
    private String email;
    private String message;
    private String type;
    private Boolean anonymous;
}