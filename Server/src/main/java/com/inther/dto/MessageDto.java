package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class MessageDto implements Serializable
{
    @Null(groups = {RequestDataValidator.AddMessage.class})
    @NotNull(groups = {RequestDataValidator.UpdateMessage.class})
    private String id;

    @Positive(groups = {RequestDataValidator.AddMessage.class})
    @Null(groups = {RequestDataValidator.UpdateMessage.class})
    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private Integer presentationId;

    @Size(groups = {RequestDataValidator.AddMessage.class, RequestDataValidator.UpdateMessage.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String message;

    @Size(groups = {RequestDataValidator.AddMessage.class, RequestDataValidator.UpdateMessage.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.AddMessage.class, RequestDataValidator.UpdateMessage.class}, regexp = "TYPE_FEEDBACK|TYPE_QUESTION")
    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String type;

    @Null(groups = {RequestDataValidator.UpdateMessage.class})
    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private Boolean anonymous;
}