package com.inther.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MessageTypeDto implements Serializable
{
    @NotNull
    private Integer messageId;

    @NotNull
    private String typeName;

    public Integer getMessageId()
    {
        return messageId;
    }

    public void setMessageId(Integer messageId)
    {
        this.messageId = messageId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}