package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "marks")
public class MarkEntity
{
    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    private UUID presentationId;

    @JsonIgnore
    private String email;
    private Integer mark;
}