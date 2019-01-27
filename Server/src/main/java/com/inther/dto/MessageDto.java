package com.inther.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MessageDto implements Serializable
{
    @NotNull
    private Integer presentationId;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String message;

    @NotNull
    private Boolean isAnonymous;

    @NotNull
    private MessageTypeDto messageType;

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

    public MessageTypeDto getMessageType()
    {
        return messageType;
    }

    public void setMessageType(MessageTypeDto messageType)
    {
        this.messageType = messageType;
    }
}