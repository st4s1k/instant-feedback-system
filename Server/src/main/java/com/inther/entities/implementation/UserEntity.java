package com.inther.entities.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inther.entities.Entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements Entities
{
    @Id
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Integer enabled;

    @OneToMany(targetEntity = UserAuthorityEntity.class, mappedBy = "email", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserAuthorityEntity> authorities;

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
    public List<UserAuthorityEntity> getAuthorities()
    {
        return authorities;
    }
    public void setAuthorities(List<UserAuthorityEntity> authorities)
    {
        this.authorities = authorities;
    }
}