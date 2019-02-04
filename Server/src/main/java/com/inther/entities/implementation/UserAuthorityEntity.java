package com.inther.entities.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inther.entities.Entities;
import javax.persistence.*;

@Entity
@Table(name = "user_authorities")
public class UserAuthorityEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @Column(name = "email")
    private String email;

    @Column(name = "authority")
    private String authority;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
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