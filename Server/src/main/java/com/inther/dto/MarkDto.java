package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class MarkDto implements Serializable
{
    @Null(groups = {RequestDataValidator.AddMark.class})
    private UUID id;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private UUID presentationId;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private UUID userId;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private Integer value;
}