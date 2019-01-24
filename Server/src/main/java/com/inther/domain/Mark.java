package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "MARKS")
public class Mark {
    @Id
    @GeneratedValue(generator = "markIdGenerator")
    @GenericGenerator(name = "markIdGenerator", strategy = "increment")
    @Column(name = "MARK_ID")
    private Integer markId;

    @Column(name = "EMAIL")
    private Integer email;

    @Column(name = "PRESENTATION_ID")
    private Integer presentationId;

    @Column(name = "MARK")
    private Integer mark;

    public Integer getMarkId()
    {
        return markId;
    }

    public void setMarkId(Integer markId)
    {
        this.markId = markId;
    }

    public Integer getEmail()
    {
        return email;
    }

    public void setEmail(Integer email)
    {
        this.email = email;
    }

    public Integer getPresentationId()
    {
        return presentationId;
    }

    public void setPresentationId(Integer presentationId)
    {
        this.presentationId = presentationId;
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