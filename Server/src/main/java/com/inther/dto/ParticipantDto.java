package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ParticipantDto
{
    @NotNull(groups = {RequestDataValidator.DeleteParticipant.class})
    private UUID id;

    @NotBlank
    private UUID presentationId;

    @NotBlank
    private String email;
}