package org.openutilities.rm.am.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RelationId implements Serializable
{
    /*private Long fromResourceId;
    private Long toResourceId;*/

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Resource fromResource;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Resource toResource;

    public void setFromResource(Resource fromResource)
    {
        this.fromResource = fromResource;
    }

    public void setToResource(Resource toResource)
    {
        this.toResource = toResource;
    }
}
