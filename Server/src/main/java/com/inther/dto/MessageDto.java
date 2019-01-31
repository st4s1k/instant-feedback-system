package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class MessageDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchRequest.class})
    @Null(groups = {RequestDataValidator.PutRequest.class})
    @NotNull(groups = {RequestDataValidator.PatchRequest.class})
    private Integer messageId;

    @Positive(groups = {RequestDataValidator.PutRequest.class})
    @Null(groups = {RequestDataValidator.PatchRequest.class})
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Integer presentationId;

    @Email(groups = {RequestDataValidator.PutRequest.class})
    @Size(groups = {RequestDataValidator.PutRequest.class}, max = 255)
    @Null(groups = {RequestDataValidator.PatchRequest.class})
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String message;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, regexp = "TYPE_FEEDBACK|TYPE_QUESTION")
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String messageType;

    @Pattern(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, regexp = "true|false")
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
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