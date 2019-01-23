package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "MESSAGE_TYPES")
public class MessageType
{
    @Id
    @GeneratedValue(generator = "messageTypeIdGenerator")
    @GenericGenerator(name = "messageTypeIdGenerator", strategy = "increment")
    @Column(name = "TYPE_ID")
    private Integer typeId;

    @Column(name = "TYPE_NAME")
    private String typeName;

    public Integer getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}