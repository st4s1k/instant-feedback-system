package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "message_types")
public class Message_types
{
    @Id
    @GeneratedValue(generator = "message_typeIdGenerator")
    @GenericGenerator(name = "message_typeIdGenerator" , strategy = "increment")
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name")
    private String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
