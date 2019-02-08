package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto implements Serializable
{
    @NotNull(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    private String id;

    @Size(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class}, max = 255)
    @Email(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    @NotBlank(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class}, min = 6, max = 16)
    @NotNull(groups = {RequestDataValidator.PutUser.class})
    private String password;

    @Valid
    @Null(groups = {RequestDataValidator.PatchUser.class})
    @NotEmpty(groups = {RequestDataValidator.PutUser.class})
    private String Role;
}