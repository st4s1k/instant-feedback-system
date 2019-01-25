package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "email")
    private Integer email;

    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "message_type")
    private Integer messageType;

    @Column(name = "message")
    private String message;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    public Integer getMessageId()
    {
        return messageId;
    }

    public void setMessageId(Integer messageId)
    {
        this.messageId = messageId;
    }

    public Integer getEmail()
    {
        return email;
    }

    public void setEmail(Integer email)
    {
        this.email = email;
    }

    public Integer getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }

    public Integer getMessageType()
    {
        return messageType;
    }

    public void setMessageType(Integer messageType)
    {
        this.messageType = messageType;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Boolean getAnonymous()
    {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous)
    {
        isAnonymous = anonymous;
    }
}