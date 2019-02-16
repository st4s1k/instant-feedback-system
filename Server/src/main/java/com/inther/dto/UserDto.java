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
public class UserDto implements Serializable
{
    @Null(groups = {RequestDataValidator.Authentication.class})
    @NotNull(groups = {RequestDataValidator.UpdateUser.class})
    private UUID id;

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

    @Null(groups = {RequestDataValidator.Authentication.class})
    @NotBlank
    private String token;
}