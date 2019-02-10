package com.inther.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.type.PostgresUUIDType;

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
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Presentation presentation;

    @ManyToOne
    private User user;

    private String message;

    private String type;

    private boolean anonymous;

    public Message setPresentation(Presentation presentation) {
        this.presentation = presentation;
        return this;
    }

    public Message setUser(User user) {
        this.user = user;
        return this;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message setType(String type) {
        this.type = type;
        return this;
    }

    public Message setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
        return this;
    }

    public Message updateBy(Message message) {
        this.presentation = message.presentation;
        this.user = message.user;
        this.message = message.message;
        this.type = message.type;
        this.anonymous = message.anonymous;
        return this;
    }
}