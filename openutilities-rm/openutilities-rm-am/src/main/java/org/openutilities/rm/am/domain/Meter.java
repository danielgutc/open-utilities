package org.openutilities.rm.am.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a meter device.
 */
@Entity(name = "Meter")
@DiscriminatorValue("2")
public class Meter extends Resource implements Serializable
{
    @Column(name = "serial_number")
    private String serialNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "toResource", cascade = CascadeType.ALL)
    @Where(clause = "type_id = 1")
    private List<Relation> usagePoints = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromResource", cascade = CascadeType.ALL)
    @Where(clause = "type_id = 2")
    private List<Relation> channels = new ArrayList<>();

    public Meter()
    {
        super(2L);
    }

    //<editor-fold desc="Getters/Setters">

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

    public List<Relation> getUsagePoints()
    {
        return usagePoints;
    }

    //</editor-fold>
}
