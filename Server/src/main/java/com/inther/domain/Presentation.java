package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRESENTATIONS")
public class Presentation
{
    @Id
    @GeneratedValue(generator = "presentationIdGenerator")
    @GenericGenerator(name = "presentationIdGenerator", strategy = "increment")
    @Column(name = "PRESENTATION_ID")
    private int presentationId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PRESENTATION_TITLE")
    private String presentationTitle;

    @Column(name = "PRESENTATION_DESCRIPTION")
    private String presentationDescription;


    @Column(name = "PRESENTATION_START_TIME")
    private Date presentationStartTime;

    @Column(name = "PRESENTATION_PLACE")
    private String presentationPlace;

    @Column(name = "PRESENTATION_END_TIME")
    private Date presentationEndTime;

    @Column(name = "PRESENTATION_MARK")
    private double presentationMark;

    public int getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(int presentationId)
    {
        this.presentationId = presentationId;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
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

    public Date getPresentationStartTime()
    {
        return presentationStartTime;
    }

    public void setPresentationStartTime(Date presentationStartTime)
    {
        this.presentationStartTime = presentationStartTime;
    }

    public String getPresentationPlace()
    {
        return presentationPlace;
    }

    public void setPresentationPlace(String presentationPlace)
    {
        this.presentationPlace = presentationPlace;
    }

    public Date getPresentationEndTime()
    {
        return presentationEndTime;
    }

    public void setPresentationEndTime(Date presentationEndTime)
    {
        this.presentationEndTime = presentationEndTime;
    }

    public double getPresentationMark()
    {
        return presentationMark;
    }

    public void setPresentationMark(double presentationMark)
    {
        this.presentationMark = presentationMark;
    }
}