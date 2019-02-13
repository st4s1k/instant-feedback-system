package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ParticipantDto
{
    @NotBlank(groups = {RequestDataValidator.DeleteParticipant.class})
    private UUID id;

    @NotBlank
    private UUID presentationId;

    @NotBlank
    private String email;
}