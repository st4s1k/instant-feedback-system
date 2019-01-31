package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class MarkDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutRequest.class})
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Integer presentationId;

    @Email(groups = {RequestDataValidator.PutRequest.class})
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String email;

    @Positive(groups = {RequestDataValidator.PutRequest.class})
    @Max(groups = {RequestDataValidator.PutRequest.class}, value = 5)
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Integer mark;

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

    public Integer getMark()
    {
        return mark;
    }

    public void setMark(Integer mark)
    {
        this.mark = mark;
    }
}