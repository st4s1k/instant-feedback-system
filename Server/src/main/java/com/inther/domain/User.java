package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User
{
    @Id
    @GeneratedValue(generator = "userIdGenerator")
    @GenericGenerator(name = "userIdGenerator", strategy = "increment")
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Integer enabled;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
}