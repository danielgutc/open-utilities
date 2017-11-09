package org.openutilities.rm.am.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data channel.
 */
@Entity(name = "Channel")
@DiscriminatorValue("3")
public class Channel extends Resource implements Serializable
{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "toResource", cascade = CascadeType.ALL)
    @Where(clause = "type_id = 2")
    private List<Relation> owner = new ArrayList<>();

    public Channel()
    {
        super(3L);
    }

    //<editor-fold desc="Getters/Setters">

    public List<Relation> getOwner()
    {
        return owner;
    }

    //</editor-fold>
}
