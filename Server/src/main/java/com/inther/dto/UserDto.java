package com.inther.dto;

import com.inther.assets.validators.RequestDataValidator;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class UserDto
{
    @Email(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class})
    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, max = 255)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class})
    private String email;

    @Size(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, min = 6, max = 16)
    @NotBlank(groups = {RequestDataValidator.PutRequest.class})
    private String password;

    @PositiveOrZero(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class})
    @Max(groups = {RequestDataValidator.PutRequest.class, RequestDataValidator.PatchRequest.class}, value = 1)
    @NotNull(groups = {RequestDataValidator.PutRequest.class})
    private Integer enabled;

    @Valid
    @NotEmpty(groups = {RequestDataValidator.PutRequest.class})
    private List<UserAuthorityDto> userAuthorities;

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

    public List<UserAuthorityDto> getUserAuthorities()
    {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthorityDto> userAuthorities)
    {
        this.userAuthorities = userAuthorities;
    }
}