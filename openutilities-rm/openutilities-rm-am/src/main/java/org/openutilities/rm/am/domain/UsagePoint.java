package org.openutilities.rm.am.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "UsagePoint")
@DiscriminatorValue("1")
public class UsagePoint extends Resource implements Serializable
{
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource")
    @Where(clause = "type_id = 1")
    private List<Relation> meters = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource")
    @Where(clause = "type_id = 2")
    private List<Relation> channels = new ArrayList<>();

    //<editor-fold desc="Boilerplate code">

    public UsagePoint()
    {
        super(1L);
    }

    public List<Relation> getMeters()
    {
        return meters;
    }

    public void setMeters(List<Relation> meters)
    {
        this.meters = meters;
    }

    public List<Relation> getChannels()
    {
        return channels;
    }

    public void setChannels(List<Relation> channels)
    {
        this.channels = channels;
    }

    //</editor-fold>
}
