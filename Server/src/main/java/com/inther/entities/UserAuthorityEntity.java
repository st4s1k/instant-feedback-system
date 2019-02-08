package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_authorities")
public class UserAuthorityEntity
{
    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    private String email;
    private String authority;
}