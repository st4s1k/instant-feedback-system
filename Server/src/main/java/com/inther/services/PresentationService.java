package com.inther.services;

import com.inther.beans.ResponseBean;
import com.inther.repositories.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class PresentationService
{
    private final PresentationRepository presentationRepository;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.presentationRepository = presentationRepository;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}