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
public class MessageDto implements Serializable
{
    @Null(groups = {RequestDataValidator.AddMessage.class})
    @NotNull(groups = {RequestDataValidator.UpdateMessage.class, RequestDataValidator.DeleteMessage.class})
    private UUID id;

    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private UUID presentationId;

    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private UUID userId;

    @Email
    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String email;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String text;

    @NotBlank(groups = {RequestDataValidator.AddMessage.class})
    private String type;

    @NotNull(groups = {RequestDataValidator.AddMessage.class})
    private Boolean anonymous;
}