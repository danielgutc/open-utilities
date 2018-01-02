package org.openutilities.core.appservices.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains an execution context parameters.
 */
public class ContextParameters implements Serializable
{
    private Map<String, Object> parameters = new HashMap<>();

    public Map<String, Object> getParameters()
    {
        return parameters;
    }
}
