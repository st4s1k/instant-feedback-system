package com.inther.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    @Email
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "enabled")
    private Integer enabled;

    @NotNull
    @OneToMany(targetEntity = UserAuthorityEntity.class, mappedBy = "email", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserAuthorityEntity> userAuthorities;

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

    public List<UserAuthorityEntity> getUserAuthorities()
    {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthorityEntity> userAuthorities)
    {
        this.userAuthorities = userAuthorities;
    }
}