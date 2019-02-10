package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class UserDto implements Serializable
{
    @NotNull(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class})
    private String id;

    @Size(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class}, max = 255)
    @Email(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class})
    @NotBlank(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class})
    private String email;

    @Size(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class}, min = 6, max = 16)
    @NotNull(groups = {RequestDataValidator.AddUser.class})
    private String password;

    @Valid
    @Null(groups = {RequestDataValidator.UpdateUser.class})
    @NotEmpty(groups = {RequestDataValidator.AddUser.class})
    private String Role;
}