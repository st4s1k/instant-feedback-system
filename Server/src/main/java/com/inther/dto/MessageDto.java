package com.inther.dto;

import com.inther.entities.implementation.MessageTypeEntity;

import java.io.Serializable;

public class MessageDto implements Serializable
{

    private Integer messageId;


    private Integer presentationId;


    private String email;


    private String message;


    private Boolean isAnonymous;


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