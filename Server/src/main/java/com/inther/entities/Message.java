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
@Table(name = "messages")
public class Message
{
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    @ManyToOne(targetEntity = Presentation.class)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID presentationId;

    @ManyToOne(targetEntity = User.class)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID userId;

    private String text;

    private String type;

    private Boolean anonymous;
}