package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.constraints.*;
import java.io.Serializable;

public class UserAuthorityDto implements Serializable
{
    @Size(groups = {RequestDataValidator.PutUserAuthority.class}, max = 255)
    @Email(groups = {RequestDataValidator.PutUserAuthority.class})
    @Null(groups = {RequestDataValidator.PutUser.class})
    @NotBlank(groups = {RequestDataValidator.PutUserAuthority.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PutUserAuthority.class}, max = 255)
    @Pattern(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PutUserAuthority.class}, regexp = "ROLE_USER|ROLE_ADMIN")
    @NotBlank(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PutUserAuthority.class})
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