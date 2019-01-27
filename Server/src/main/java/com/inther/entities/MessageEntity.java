package com.inther.entities;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

    @OneToOne(targetEntity = MessageTypeEntity.class, mappedBy = "message_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MessageTypeEntity messageType;

    public Integer getMessageId()
    {
        return messageId;
    }

    public void setMessageId(Integer messageId)
    {
        this.messageId = messageId;
    }

    public Integer getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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

    public MessageTypeEntity getMessageType()
    {
        return messageType;
    }

    public void setMessageType(MessageTypeEntity messageType)
    {
        this.messageType = messageType;
    }
}