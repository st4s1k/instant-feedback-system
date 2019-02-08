package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class MarkDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PutMark.class})
    @NotNull(groups = {RequestDataValidator.PutMark.class})
    private String presentationId;

    @Positive(groups = {RequestDataValidator.PutMark.class})
    @Max(groups = {RequestDataValidator.PutMark.class}, value = 5)
    @NotNull(groups = {RequestDataValidator.PutMark.class})
    private Integer mark;
}