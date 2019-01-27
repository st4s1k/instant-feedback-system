package com.inther.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable
{
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Integer enabled;

    @NotNull
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