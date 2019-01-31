package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class UserAuthorityDto implements Serializable
{
    @Email(groups = {RequestDataValidator.PutRequest.class})
    @Size(groups = {RequestDataValidator.PutRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String authority;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}