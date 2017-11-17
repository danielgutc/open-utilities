package org.openutilities.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an usage point.
 */
public class UsagePoint extends Resource implements Serializable
{
    private List<Relation> meters = new ArrayList<>();
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
