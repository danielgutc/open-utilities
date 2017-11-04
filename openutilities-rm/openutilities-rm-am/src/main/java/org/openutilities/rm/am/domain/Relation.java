package org.openutilities.rm.am.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relations")
@IdClass(RelationId.class)
public class Relation implements Serializable
{
    @Id
    @Column(name = "from_res_id")
    private Long fromResourceId;

    @Id
    @Column(name = "to_res_id")
    private Long toResourceId;

    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "from_res_id", referencedColumnName = "id")
    private Resource fromResource;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "to_res_id", referencedColumnName = "id")
    private Resource toResource;

    //<editor-fold desc="Boilerplate code code">

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
