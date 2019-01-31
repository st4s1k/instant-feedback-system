package com.inther.entities.implementation;

import com.inther.entities.Entities;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "presentations")
public class PresentationEntity implements Entities
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id")
    private Integer presentationId;

    @Column(name = "email")
    private String email;

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

    @OneToMany(targetEntity = ParticipantEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParticipantEntity> presentationParticipants;

    @OneToMany(targetEntity = MessageEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MessageEntity> presentationMessages;

    @OneToMany(targetEntity = MarkEntity.class, mappedBy = "presentationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MarkEntity> presentationMarks;

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