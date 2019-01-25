package com.inther.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Integer enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "email", cascade = CascadeType.ALL)
    private List<UserAuthority> userAuthorities;

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

    public List<UserAuthority> getUserAuthorities()
    {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthority> userAuthorities)
    {
        this.userAuthorities = userAuthorities;
    }
}