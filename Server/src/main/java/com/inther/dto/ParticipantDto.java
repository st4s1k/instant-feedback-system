package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class ParticipantDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutParticipant.class})
    @NotNull(groups = {RequestDataValidator.PutParticipant.class})
    private String presentationId;
}