package com.inther.services;

import com.inther.beans.AdminAuthorityValidatorBean;
import com.inther.beans.ResponseBean;
import com.inther.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class MessageService
{
    private final AdminAuthorityValidatorBean adminAuthorityValidatorBean;
    private final MessageRepository messageRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @Autowired
    public MessageService(AdminAuthorityValidatorBean adminAuthorityValidatorBean, MessageRepository messageRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.adminAuthorityValidatorBean = adminAuthorityValidatorBean;
        this.messageRepository = messageRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}