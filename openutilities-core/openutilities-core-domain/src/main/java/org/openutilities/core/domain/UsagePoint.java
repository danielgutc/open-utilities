package org.openutilities.core.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an usage point.
 */
@Entity(name = "UsagePoint")
@DiscriminatorValue("1")
public class UsagePoint extends Resource implements Serializable
{
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource", cascade = CascadeType.ALL)
    @Where(clause = "type_id = 1")
    private List<Relation> meters = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource", cascade = CascadeType.ALL)
    @Where(clause = "type_id = 2")
    private List<Relation> channels = new ArrayList<>();

    public UsagePoint()
    {
        super(1L);
    }

    //<editor-fold desc="Getters/Setters">

    public List<Relation> getMeters()
    {
        return meters;
    }

    public List<Relation> getChannels()
    {
        return channels;
    }

    //</editor-fold>
}
