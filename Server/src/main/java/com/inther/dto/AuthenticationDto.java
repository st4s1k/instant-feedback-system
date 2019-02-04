package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthenticationDto
{
    @Email(groups = {RequestDataValidator.PutAuthentication.class})
    @Size(groups = {RequestDataValidator.PutAuthentication.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutAuthentication.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutAuthentication.class}, min = 6, max = 16)
    @NotBlank(groups = {RequestDataValidator.PutAuthentication.class})
    private String password;

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}