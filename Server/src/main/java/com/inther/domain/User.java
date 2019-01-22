package com.inther.domain;


import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User
{
    @Id
    @GeneratedValue(generator = "userIdGenerator")
    @SequenceGenerator(name = "userIdGenerator", sequenceName = "question_sequence")
    @Column(name = "USER_ID")
    private int userId;


}