package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

@Data
public class MessageDto implements Serializable
{
    @Positive(groups = {RequestDataValidator.PatchMessage.class})
    @Null(groups = {RequestDataValidator.PutMessage.class})
    @NotNull(groups = {RequestDataValidator.PatchMessage.class})
    private String id;

    @Positive(groups = {RequestDataValidator.PutMessage.class})
    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Integer presentationId;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String message;

    @Size(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutMessage.class, RequestDataValidator.PatchMessage.class}, regexp = "TYPE_FEEDBACK|TYPE_QUESTION")
    @NotBlank(groups = {RequestDataValidator.PutMessage.class})
    private String type;

    @Null(groups = {RequestDataValidator.PatchMessage.class})
    @NotNull(groups = {RequestDataValidator.PutMessage.class})
    private Boolean anonymous;
}