package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole
{
    @Id
    @GeneratedValue(generator = "userRoleIdGenerator")
    @GenericGenerator(name = "userRoleIdGenerator", strategy = "increment")
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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