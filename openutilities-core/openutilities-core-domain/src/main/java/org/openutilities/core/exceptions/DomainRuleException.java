package org.openutilities.core.exceptions;

import org.openutilities.core.domain.Resource;

/**
 * Custom runtime exception
 */
public class DomainRuleException extends RuntimeException
{
    public DomainRuleException(String message)
    {
        super(message);
    }

    public DomainRuleException(Resource resource, String message)
    {
        super(String.format("%s - %s", resource.getCode(), message));
    }

    public DomainRuleException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    public DomainRuleException(Resource resource, String message, Throwable throwable)
    {
        super(String.format("%s - %s", resource.getCode(), message), throwable);
    }
}
