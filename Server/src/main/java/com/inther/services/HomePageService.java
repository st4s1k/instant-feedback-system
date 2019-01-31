package com.inther.services;

import com.inther.beans.AuthorityUtilityBean;
import com.inther.beans.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HomePageService
{
    private final AuthorityUtilityBean authorityUtilityBean;
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    public ResponseBean getHomePage()
    {
        responseBean.setHeaders(httpHeaders);
        responseBean.setResponse(HttpStatus.OK);
        responseBean.setResponse("You successfully authenticated as " + authorityUtilityBean.getCurrentAuthenticationEmail());
        return responseBean;
    }

    @Autowired
    public HomePageService(AuthorityUtilityBean authorityUtilityBean, ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.authorityUtilityBean = authorityUtilityBean;
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}