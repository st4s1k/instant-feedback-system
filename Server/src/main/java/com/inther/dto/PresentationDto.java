package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
public class PresentationDto implements Serializable
{
    @NotNull
    private String id;

    @Null(groups = {  RequestDataValidator.UpdatePresentation.class })
    @NotNull(groups = {
            RequestDataValidator.GetPresentationPage.class,
            RequestDataValidator.AddPresentation.class
    })
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotBlank
    private String place;

    @Null
    @NotNull(groups = {
            RequestDataValidator.AddPresentation.class,
            RequestDataValidator.UpdatePresentation.class
    })
    private List<ParticipantDto> participants;

    @Null
    @NotNull(groups = { RequestDataValidator.GetPresentationPage.class })
    private List<MessageDto> messages;

    @Null
    @NotNull(groups = { RequestDataValidator.GetPresentationPage.class })
    private List<MarkDto> marks;
}