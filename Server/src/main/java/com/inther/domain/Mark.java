package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mark_id")
    private Integer markId;

    @Column(name = "email")
    private Integer email;

    @Column(name = "presentation_id")
    private Integer presentationId;

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