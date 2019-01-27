package com.inther.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MarkDto implements Serializable
{
    @NotNull
    private Integer presentationId;

    @NotNull
    private String email;

    @NotNull
    private Integer mark;

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