package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private Integer voteCount;

    private Boolean finished;

    private Boolean started;
}