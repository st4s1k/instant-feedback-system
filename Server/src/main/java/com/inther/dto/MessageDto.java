package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class MessageDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchMessage.class})
    @Null(groups = {RequestDataValidator.PutMessage.class})
    @NotNull(groups = {RequestDataValidator.PatchMessage.class})
    private Integer messageId;

    @Positive(groups = {RequestDataValidator.PutMessage.class})
    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Integer presentationId;

    @Email(groups = {RequestDataValidator.PutMessage.class})
    @Size(groups = {RequestDataValidator.PutMessage.class}, max = 255)
    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String message;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, regexp = "TYPE_FEEDBACK|TYPE_QUESTION")
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String messageType;

    @Pattern(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, regexp = "true|false")
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Boolean isAnonymous;

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
    public String getMessageType()
    {
        return messageType;
    }
    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }
    public Boolean getIsAnonymous()
    {
        return isAnonymous;
    }
    public void setIsAnonymous(Boolean isAnonymous)
    {
        this.isAnonymous = isAnonymous;
    }
}