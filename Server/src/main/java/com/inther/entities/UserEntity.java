package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    private UUID id;
    private String email;

    @JsonIgnore
    private String password;
    private Boolean enabled;

    @OneToMany(targetEntity = UserAuthorityEntity.class, mappedBy = "email", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserAuthorityEntity> authorities;
}