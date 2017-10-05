package org.openutilities.proc.core.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Encapsulates job parameters.
 * TODO Support different configuration sets for the same job
 */
public class JobParameters
{
    private final Map<String, Object> params;

    /**
     * Initializes the job parameters object with a map containing the values.
     * @param params is the map containing the values. An empty HashMap is created if the parameter is null.
     */
    public JobParameters(Map<String, Object> params)
    {
        this.params = Optional.ofNullable(params).orElse(new HashMap<>());
    }

    /**
     * Generic method to get casted parameters.
     * @param paramName is the parameter name
     * @param <T> is the Type to which the parameter is casted
     * @return the casted parameter value
     */
    public <T> T getTypedParamValue(String paramName)
    {
        return (T) this.params.get(paramName);
    }

    /**
     * Get parameter without casting.
     * @param paramName is the parameter name
     * @return the parameter value
     */
    public Object getParamValue(String paramName)
    {
        return this.params.get(paramName);
    }

    /**
     * Get parameter as Long.
     * @param paramName is the parameter name
     * @return the parameter value casted as Long
     */
    public Long getParamValueAsLong(String paramName)
    {
        return this.<Long>getTypedParamValue(paramName);
    }

    /**
     * Get parameter as String.
     * @param paramName is the parameter name
     * @return the parameter value casted as String
     */
    public String getParamValueAsString(String paramName)
    {
        return this.<String>getTypedParamValue(paramName);
    }

}
