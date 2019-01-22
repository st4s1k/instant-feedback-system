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
    @GenericGenerator(name = "presentationIdGenerator" , strategy = "increment")

    @Column(name = "presentation_id")
    private int presentationId;

    @Column(name = "presentation_title")
    private String presentationTitle;

    @Column(name = "presentation_time")
    private Date presentationTime;

    @Column(name = "presentation_place")
    private String presentationPlace;

    @Column(name = "presentation_duration")
    private String presentationDuration;

    @Column(name = "presentation_mark")
    private double presentationMark;

    @ManyToOne


    public int getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(int presentationId) {
        this.presentationId = presentationId;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public Date getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(Date presentationTime) {
        this.presentationTime = presentationTime;
    }

    public String getPresentationPlace() {
        return presentationPlace;
    }

    public void setPresentationPlace(String presentationPlace) {
        this.presentationPlace = presentationPlace;
    }

    public String getPresentationDuration() {
        return presentationDuration;
    }

    public void setPresentationDuration(String presentationDuration) {
        this.presentationDuration = presentationDuration;
    }

    public double getPresentationMark() {
        return presentationMark;
    }

    public void setPresentationMark(double presentationMark) {
        this.presentationMark = presentationMark;
    }
}
