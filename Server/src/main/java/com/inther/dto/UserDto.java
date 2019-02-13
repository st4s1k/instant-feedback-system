package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class UserDto implements Serializable
{
    @Null(groups = {RequestDataValidator.Authentication.class})
    @NotBlank(groups = {RequestDataValidator.UpdateUser.class})
    private String id;

    @Email
    @NotBlank(groups = {
            RequestDataValidator.AddUser.class,
            RequestDataValidator.UpdateUser.class,
            RequestDataValidator.Authentication.class
    })
    private String email;

    @NotBlank(groups = {
            RequestDataValidator.AddUser.class,
            RequestDataValidator.Authentication.class
    })
    private String password;

    @Null(groups = {RequestDataValidator.Authentication.class})
    @NotBlank(groups = {RequestDataValidator.AddUser.class})
    private String Role;
}