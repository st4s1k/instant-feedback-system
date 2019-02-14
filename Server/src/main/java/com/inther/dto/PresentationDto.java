package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class PresentationDto implements Serializable
{
    @NotNull(groups = {RequestDataValidator.UpdatePresentation.class})
    private UUID id;

    @NotBlank(groups = {RequestDataValidator.UpdatePresentation.class, RequestDataValidator.AddPresentation.class})
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @NotBlank
    private String date;

    @NotBlank
    private String place;

    private Double avgMark;
}