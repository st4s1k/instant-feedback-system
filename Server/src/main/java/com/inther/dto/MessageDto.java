package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class MessageDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchMessage.class})
    @Null(groups = {RequestDataValidator.PutMessage.class})
    @NotNull(groups = {RequestDataValidator.PatchMessage.class})
    private Integer id;

    @Positive(groups = {RequestDataValidator.PutMessage.class})
    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Integer presentationId;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String message;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, regexp = "TYPE_FEEDBACK|TYPE_QUESTION")
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String type;

    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Boolean isAnonymous;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
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