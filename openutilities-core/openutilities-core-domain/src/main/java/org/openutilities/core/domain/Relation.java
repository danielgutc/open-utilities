package org.openutilities.core.domain;

import java.io.Serializable;

/**
 * Represents a temporal relation between 2 resources
 */
public class Relation implements Serializable
{
    public static final Long UP_TO_METER = 1L;
    public static final Long ANY_TO_CHANNEL = 2L;

    private Long fromResourceId;
    private Long toResourceId;
    private Long typeId;
    private Resource fromResource;
    private Resource toResource;

    public Relation()
    {
    }

    public Relation(Resource fromResource, Resource toResource, Long typeId)
    {
        this.typeId = typeId;
        this.setFromResource(fromResource);
        this.setToResource(toResource);
    }

    //<editor-fold desc="Getters/Setters">

    public Long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Resource getFromResource()
    {
        return fromResource;
    }

    public void setFromResource(Resource fromResource)
    {
        this.fromResource = fromResource;
    }

    public Resource getToResource()
    {
        return toResource;
    }

    public void setToResource(Resource toResource)
    {
        this.toResource = toResource;
    }

    public Long getFromResourceId()
    {
        return fromResourceId;
    }

    public Long getToResourceId()
    {
        return toResourceId;
    }

    //</editor-fold>
}
