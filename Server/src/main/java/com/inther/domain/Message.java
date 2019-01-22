package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Message
{
    @Id
    @GeneratedValue(generator = "messageIdGenerator")
    @GenericGenerator(name = "messageIdGenerator", strategy = "increment")
    @Column(name = "MESSAGE_ID")
    private Integer messageId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PRESENTATION_ID")
    private Integer presentationId;

    @Column(name = "MESSAGE_TYPE")
    private Integer messageType;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "IS_ANONYMOUS")
    private Boolean isAnonymous;

    public Integer getMessageId()
    {
        return messageId;
    }

    public void setMessageId(Integer messageId)
    {
        this.messageId = messageId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
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