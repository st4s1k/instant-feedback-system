package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
public class ParticipantDto
{
    @NotNull(groups = {RequestDataValidator.DeleteParticipant.class})
    private UUID id;

    @NotNull
    private UUID presentationId;

    @NotNull
    private String email;
}