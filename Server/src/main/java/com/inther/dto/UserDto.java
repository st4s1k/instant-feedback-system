package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable
{
    @Size(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class}, max = 255)
    @Email(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    @NotBlank(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class}, min = 6, max = 16)
    @NotNull(groups = {RequestDataValidator.PutUser.class})
    private String password;

    @PositiveOrZero(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class})
    @Max(groups = {RequestDataValidator.PutUser.class, RequestDataValidator.PatchUser.class}, value = 1)
    @NotNull(groups = {RequestDataValidator.PutUser.class})
    private Integer enabled;

    @Valid
    @Null(groups = {RequestDataValidator.PatchUser.class})
    @NotEmpty(groups = {RequestDataValidator.PutUser.class})
    private List<UserAuthorityDto> authorities;

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
    public Integer getEnabled()
    {
        return enabled;
    }
    public void setEnabled(Integer enabled)
    {
        this.enabled = enabled;
    }
    public List<UserAuthorityDto> getAuthorities()
    {
        return authorities;
    }
    public void setAuthorities(List<UserAuthorityDto> authorities)
    {
        this.authorities = authorities;
    }
}