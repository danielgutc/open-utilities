package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.openutilities.core.util.RelationsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an usage point.
 */
public class UsagePoint extends Resource implements Serializable, Verifiable
{
    @JsonManagedReference
    private List<Relation> meters = new ArrayList<>();
    @JsonManagedReference
    private List<Relation> channels = new ArrayList<>();

    public UsagePoint()
    {
        super(1L);
    }

    @Override
    public void verify()
    {
        super.verify();

        // Verify children relations
        RelationsUtils.verifyRelations(meters, this);
        RelationsUtils.verifyRelations(channels, this);

        // Verify children
        meters.stream().forEach(mr -> ((Meter)mr.getToResource()).verify());
        channels.stream().forEach(cr -> ((Channel)cr.getToResource()).verify());
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
