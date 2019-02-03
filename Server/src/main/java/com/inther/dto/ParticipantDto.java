package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class ParticipantDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutParticipant.class})
    @NotNull(groups = {RequestDataValidator.PutParticipant.class})
    private Integer presentationId;

    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
}