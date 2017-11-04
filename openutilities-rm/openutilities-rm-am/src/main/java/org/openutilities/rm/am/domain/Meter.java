package org.openutilities.rm.am.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Meter")
@DiscriminatorValue("2")
public class Meter extends Resource implements Serializable
{
    @Column(name = "serial_number")
    private String serialNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "toResource")
    @Where(clause = "type_id = 1")
    private List<Relation> usagePoints = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource")
    @Where(clause = "type_id = 2")
    private List<Relation> channels = new ArrayList<>();

    //<editor-fold desc="Boilerplate code">

    public Meter()
    {
        super(2L);
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
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
