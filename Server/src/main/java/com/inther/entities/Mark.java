package com.inther.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "marks")
public class Mark
{
    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(targetEntity = Presentation.class, fetch = FetchType.LAZY)
    @Type(type = "pg-uuid")
    private UUID presentationId;

    @JsonIgnore
    private String email;
    private Integer mark;
}