package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthenticationDto
{
    @Email(groups = {RequestDataValidator.PutAuthentication.class})
    @Size(groups = {RequestDataValidator.PutAuthentication.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutAuthentication.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutAuthentication.class}, min = 6, max = 16)
    @NotBlank(groups = {RequestDataValidator.PutAuthentication.class})
    private String password;
}