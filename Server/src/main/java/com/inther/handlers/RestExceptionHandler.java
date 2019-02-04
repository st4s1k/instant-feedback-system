package com.inther.handlers;

import com.inther.assets.wrappers.ResponseEntityWrapper;
import com.inther.beans.ResponseBean;
import com.inther.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.DateTimeException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    private final ResponseBean responseBean;
    private final HttpHeaders httpHeaders;

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<?> handleBadRequestException(BadRequestException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DeleteLastAuthorityException.class)
    public final ResponseEntity<?> handleDeleteLastAuthorityException(DeleteLastAuthorityException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(DuplicatedEntryException.class)
    public final ResponseEntity<?> handleDuplicatedEntryException(DuplicatedEntryException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NotFoundEntryException.class)
    public final ResponseEntity<?> handleNotFoundEntryException(NotFoundEntryException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SelfDestructionException.class)
    public final ResponseEntity<?> handleSelfDestructionException(SelfDestructionException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DateTimeException.class)
    public final ResponseEntity<?> handleDateTimeException(DateTimeException e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleInternalServerException(Exception e, WebRequest request)
    {
        return handleExceptionInternal(e, null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
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