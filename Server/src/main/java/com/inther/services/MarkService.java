package com.inther.services;

import com.inther.beans.AdminAuthorityValidatorBean;
import com.inther.beans.ResponseBean;
import com.inther.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class MarkService
{
    private final AdminAuthorityValidatorBean adminAuthorityValidatorBean;
    private final MarkRepository markRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @Autowired
    public MarkService(AdminAuthorityValidatorBean adminAuthorityValidatorBean, MarkRepository markRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.adminAuthorityValidatorBean = adminAuthorityValidatorBean;
        this.markRepository = markRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}