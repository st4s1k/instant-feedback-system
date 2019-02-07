package com.inther.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseBean
{
    @JsonIgnore
    private HttpHeaders headers;

    @JsonIgnore
    private HttpStatus status;

    private Object response;
}