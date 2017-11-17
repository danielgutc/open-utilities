package org.openutilities.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a meter device.
 */
public class Meter extends Resource implements Serializable
{
    private String serialNumber;
    private List<Relation> usagePoints = new ArrayList<>();
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
