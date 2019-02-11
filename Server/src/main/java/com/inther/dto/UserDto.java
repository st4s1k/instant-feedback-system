package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class UserDto implements Serializable
{
    @NotNull(groups = {RequestDataValidator.UpdateUser.class})
    @Null(groups = {RequestDataValidator.AddUser.class})
    private String id;

    @Email
    @NotBlank(groups = {RequestDataValidator.AddUser.class, RequestDataValidator.UpdateUser.class})
    private String email;

    @NotNull(groups = {RequestDataValidator.AddUser.class})
    private String password;

    @Valid
//    @Null(groups = {RequestDataValidator.UpdateUser.class})
    @NotEmpty(groups = {RequestDataValidator.AddUser.class})
    private String Role;
}