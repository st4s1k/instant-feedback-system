package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "presentations")
public class Presentation
{
    @Id
    @GeneratedValue(generator = "presentationIdGenerator")
    @GenericGenerator(name = "presentationIdGenerator", strategy = "increment")
    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private Integer email;

    @Column(name = "presentation_title")
    private String presentationTitle;

    @Column(name = "presentation_description")
    private String presentationDescription;

    @Column(name = "presentation_start_date")
    private Date presentationStartDate;

    @Column(name = "presentation_place")
    private String presentationPlace;

    @Column(name = "presentation_end_date")
    private Date presentationEndDate;

    @Column(name = "presentation_mark")
    private Float presentationMark;

    public Integer getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
    }

    public Integer getEmail()
    {
        return email;
    }

    public void setEmail(Integer email)
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

    public Float getPresentationMark()
    {
        return presentationMark;
    }

    public void setPresentationMark(Float presentationMark)
    {
        this.presentationMark = presentationMark;
    }
}