package com.inther.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Messages
{
    @Id
    @GeneratedValue(generator = "messageIdGenerator")
    @GenericGenerator(name = "messageIdGenerator" , strategy = "increment")
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "is_anonymous")
    private boolean isAnonymous;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
