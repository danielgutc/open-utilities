package org.openutilities.rm.am.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "relations")
//@IdClass(RelationId.class)
@AssociationOverrides({
        @AssociationOverride(name ="pk.fromResource", joinColumns = @JoinColumn(name ="from_res_id")),
        @AssociationOverride(name ="pk.toResource", joinColumns = @JoinColumn(name ="to_res_id"))
})
public class Relation implements Serializable
{
    public static final Long UP_TO_METER = 1L;
    public static final Long ANY_TO_CHANNEL = 2L;

    @EmbeddedId
    private RelationId pk = new RelationId();
//    @Id
//    @Column(name = "from_res_id")
//    private Long fromResourceId;

//    @Id
//    @Column(name = "to_res_id")
//    private Long toResourceId;

    @Column(name = "type_id")
    private Long typeId;


    public Relation()
    {
    }

    public Relation(Resource fromResource, Resource toResource, Long typeId)
    {
        //this.fromResourceId = fromResourceId;
        //this.toResourceId = toResourceId;
        this.typeId = typeId;
        this.pk.setFromResource(fromResource);
        this.pk.setToResource(toResource);
    }

    //<editor-fold desc="Getters/Setters">

   /* public Long getTypeId()
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
*/
    //</editor-fold>
}
