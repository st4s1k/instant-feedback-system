package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
@Data
public class PresentationDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.updatePresentation.class})
    @Null(groups = {RequestDataValidator.postPresentation.class})
    @NotNull(groups = {RequestDataValidator.updatePresentation.class})
    private String id;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String title;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String description;

    @NotNull(groups = {RequestDataValidator.postPresentation.class})
    private String startDate;

    @NotNull(groups = {RequestDataValidator.postPresentation.class})
    private String endDate;

    @Size(groups = {RequestDataValidator.postPresentation.class, RequestDataValidator.updatePresentation.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.postPresentation.class})
    private String place;
}