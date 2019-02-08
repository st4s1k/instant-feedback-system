package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity
{
    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    private UUID presentationId;
    private String email;
    private String message;
    private String type;
    private Boolean anonymous;
}