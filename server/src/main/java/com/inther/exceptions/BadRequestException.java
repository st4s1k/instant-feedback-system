package com.inther.exceptions;

public final class BadRequestException extends Exception
{
    public BadRequestException(String message)
    {
        super(message);
    }
}