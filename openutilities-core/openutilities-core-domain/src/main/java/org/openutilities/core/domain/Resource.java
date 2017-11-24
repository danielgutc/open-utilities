package org.openutilities.core.domain;

import org.openutilities.core.exceptions.DomainRuleException;

import java.io.Serializable;

/**
 * Represents a resource.
 */
public class Resource implements Serializable, Verifiable
{
    protected Long id;
    protected Long typeId;
    protected Long specId;
    protected String code;

    public Resource() {}

    public Resource(Long typeId)
    {
        this.typeId = typeId;
    }

    @Override
    public void verify()
    {
        if (typeId == null || specId == null || code == null || code.isEmpty())
        {
            throw new DomainRuleException(this, "Not all properties are available");
        }
    }

    //<editor-fold desc="Getters/Setters">

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getSpecId()
    {
        return specId;
    }

    public void setSpecId(Long specId)
    {
        this.specId = specId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    //</editor-fold>
}
