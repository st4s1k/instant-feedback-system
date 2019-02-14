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

    @ManyToOne
    private Presentation presentation;

    @ManyToOne
    private User user;

    private String text;

    private String type;

    private Boolean anonymous;

    public String getEmail() {
        return this.user.getEmail();
    }

    public String getUserId() {
        return this.user.getId().toString();
    }

    public String getPresentationId() {
        return this.presentation.getId().toString();
    }
}