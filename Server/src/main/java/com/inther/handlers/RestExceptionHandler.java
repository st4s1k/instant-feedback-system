package com.inther.handlers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.beans.ResponseBean;
import com.inther.exceptions.BadCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Object> handle(BadCredentialsException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
        {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }
        responseBean.setHeaders(headers);
        responseBean.setStatus(status);
        responseBean.setResponse(e.getMessage());
        return new ResponseEntityWrapper<>(responseBean);
    }

    @Autowired
    public RestExceptionHandler(ResponseBean responseBean, HttpHeaders httpHeaders)
    {
        this.responseBean = responseBean;
        this.httpHeaders = httpHeaders;
    }
}