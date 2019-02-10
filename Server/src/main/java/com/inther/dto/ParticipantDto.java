package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
public class ParticipantDto implements Serializable
{

    @Null(groups = {RequestDataValidator.AddParticipant.class})
    private String id;

    @NotNull(groups = {RequestDataValidator.AddParticipant.class})
    private String presentationId;

    @NotNull(groups = {RequestDataValidator.AddParticipant.class})
    private String email;
}