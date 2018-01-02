package org.openutilities.core.appservices.exception;

public class AppServicesException extends RuntimeException
{
    public AppServicesException(String message)
    {
        super(message);
    }

    public AppServicesException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
