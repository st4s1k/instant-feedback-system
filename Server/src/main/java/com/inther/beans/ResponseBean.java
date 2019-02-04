package com.inther.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBean
{
    @JsonIgnore
    private HttpHeaders headers;

    @JsonIgnore
    private HttpStatus status;

    private Object response;

    public HttpHeaders getHeaders()
    {
        return headers;
    }
    public void setHeaders(HttpHeaders headers)
    {
        this.headers = headers;
    }
    public HttpStatus getStatus()
    {
        return status;
    }
    public void setStatus(HttpStatus status)
    {
        this.status = status;
    }
    public Object getResponse()
    {
        return response;
    }
    public void setResponse(Object response)
    {
        this.response = response;
    }
}