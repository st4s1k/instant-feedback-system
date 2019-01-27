package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class MessageService
{
    private final MessageRepository messageRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @Autowired
    public MessageService(MessageRepository messageRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.messageRepository = messageRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}