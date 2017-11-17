package org.openutilities.core.domain;

import java.io.Serializable;

/**
 * Represents a resource.
 */
public class Resource implements Serializable
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
