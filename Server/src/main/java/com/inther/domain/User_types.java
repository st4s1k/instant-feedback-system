package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_types")
public class User_types
{
    @Id
    @GeneratedValue(generator = "user_typeIdGenerator")
    @GenericGenerator(name = "user_typeIdGenerator" , strategy = "increment")
    @Column(name = "type_id")
    private int user_typeId;

    @Column(name = "type_name")
    private String type_name;

    public int getUser_typeId() {
        return user_typeId;
    }

    public void setUser_typeId(int user_typeId) {
        this.user_typeId = user_typeId;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
