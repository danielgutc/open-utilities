package org.openutilities.rm.am.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a temporal relation between 2 resources
 */
@Entity
@Table(name = "relations")
@IdClass(RelationId.class)
public class Relation implements Serializable
{
    public static final Long UP_TO_METER = 1L;
    public static final Long ANY_TO_CHANNEL = 2L;

    @Id
    @Column(name = "from_res_id")
    private Long fromResourceId;

    @Id
    @Column(name = "to_res_id")
    private Long toResourceId;

    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "from_res_id", referencedColumnName = "id")
    private Resource fromResource;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "to_res_id", referencedColumnName = "id")
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

    //</editor-fold>
}
