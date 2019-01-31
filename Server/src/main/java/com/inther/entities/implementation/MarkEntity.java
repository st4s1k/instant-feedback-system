package com.inther.entities.implementation;

import com.inther.entities.Entities;
import javax.persistence.*;

@Entity
@Table(name = "marks")
public class MarkEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer markId;

    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private String email;

    @Column(name = "mark")
    private Integer mark;

    public Integer getMarkId()
    {
        return markId;
    }
    public void setMarkId(Integer markId)
    {
        this.markId = markId;
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