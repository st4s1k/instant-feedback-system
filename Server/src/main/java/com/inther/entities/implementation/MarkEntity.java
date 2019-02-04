package com.inther.entities.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inther.entities.Entities;
import javax.persistence.*;

@Entity
@Table(name = "marks")
public class MarkEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @Column(name = "presentation_id")
    private Integer presentationId;

    @JsonIgnore
    @Column(name = "email")
    private String email;

    @Column(name = "mark")
    private Integer mark;

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getPresentationId()
    {
        return presentationId;
    }
    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public Integer getMark()
    {
        return mark;
    }
    public void setMark(Integer mark)
    {
        this.mark = mark;
    }
}