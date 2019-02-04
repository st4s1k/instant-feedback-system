package com.inther.entities.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inther.entities.Entities;
import javax.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    @Column(name = "is_anonymous")
    private Boolean isAnonymous;

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
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public Boolean getAnonymous()
    {
        return isAnonymous;
    }
    public void setAnonymous(Boolean anonymous)
    {
        isAnonymous = anonymous;
    }
}