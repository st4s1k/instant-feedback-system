package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ParticipantDto
{
    @NotBlank(groups = {RequestDataValidator.DeleteParticipant.class})
    private UUID id;

    @NotNull
    private UUID presentationId;

    @NotNull
    private String email;
}