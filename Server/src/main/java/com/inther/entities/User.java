package com.inther.entities;

import com.inther.dto.AuthenticationDto;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class User
{
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;

    private String password;

    private String role;

    public User() { }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(AuthenticationDto authenticationDto) {
        this.email = authenticationDto.getEmail();
        this.password = authenticationDto.getPassword();
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }
}