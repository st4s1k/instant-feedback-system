package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class MarkDto implements Serializable
{
    @Null(groups = {RequestDataValidator.AddMark.class})
    private String id;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private String presentationId;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private String userId;

    @NotNull(groups = {RequestDataValidator.AddMark.class})
    private Integer value;
}