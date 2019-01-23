package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRole
{
    @Id
    @GeneratedValue(generator = "userRoleIdGenerator")
    @GenericGenerator(name = "userRoleIdGenerator", strategy = "increment")
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ROLE")
    private String role;

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}