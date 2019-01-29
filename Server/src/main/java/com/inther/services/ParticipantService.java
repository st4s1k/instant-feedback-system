package com.inther.services;

import com.inther.beans.AdminAuthorityValidatorBean;
import com.inther.beans.ResponseBean;
import com.inther.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService
{
    private final AdminAuthorityValidatorBean adminAuthorityValidatorBean;
    private final ParticipantRepository participantRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @Autowired
    public ParticipantService(AdminAuthorityValidatorBean adminAuthorityValidatorBean, ParticipantRepository participantRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.adminAuthorityValidatorBean = adminAuthorityValidatorBean;
        this.participantRepository = participantRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}