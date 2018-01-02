package org.openutilities.core.appservices.exception;

public class WorkflowExecutionException extends RuntimeException
{
    public WorkflowExecutionException(String message)
    {
        super(message);
    }

    public WorkflowExecutionException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
