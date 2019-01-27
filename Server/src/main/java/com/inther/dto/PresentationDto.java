package com.inther.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class PresentationDto implements Serializable
{
    @Email
    @NotNull
    private String email;

    @NotNull
    private String presentationTitle;

    @NotNull
    private String presentationDescription;

    @NotNull
    private Date presentationStartDate;

    @NotNull
    private String presentationPlace;

    @NotNull
    private Date presentationEndDate;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPresentationTitle()
    {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle)
    {
        this.presentationTitle = presentationTitle;
    }

    public String getPresentationDescription()
    {
        return presentationDescription;
    }

    public void setPresentationDescription(String presentationDescription)
    {
        this.presentationDescription = presentationDescription;
    }

    public Date getPresentationStartDate()
    {
        return presentationStartDate;
    }

    public void setPresentationStartDate(Date presentationStartDate)
    {
        this.presentationStartDate = presentationStartDate;
    }

    public String getPresentationPlace()
    {
        return presentationPlace;
    }

    public void setPresentationPlace(String presentationPlace)
    {
        this.presentationPlace = presentationPlace;
    }

    public Date getPresentationEndDate()
    {
        return presentationEndDate;
    }

    public void setPresentationEndDate(Date presentationEndDate)
    {
        this.presentationEndDate = presentationEndDate;
    }
}