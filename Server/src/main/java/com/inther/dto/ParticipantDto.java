package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class ParticipantDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutParticipant.class})
    @NotNull(groups = {RequestDataValidator.PutParticipant.class})
    private Integer presentationId;

    @Email(groups = {RequestDataValidator.PutParticipant.class})
    @NotBlank(groups = {RequestDataValidator.PutParticipant.class})
    private String email;

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
}