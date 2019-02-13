package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class MessageDto implements Serializable
{
    @NotBlank(groups = {RequestDataValidator.UpdateMessage.class})
    private String id;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String presentationId;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String userId;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String message;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String type;

    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private Boolean anonymous;
}