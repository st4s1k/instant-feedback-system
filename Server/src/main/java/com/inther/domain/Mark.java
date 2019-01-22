package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark
{
    @Id
    @GeneratedValue(generator = "markIdGenerator")
    @GenericGenerator(name = "markIdGenerator" , strategy = "increment")
    @Column(name = "mark_id")
    private int markId;


    @Column(name = "mark")
    private int mark;

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
