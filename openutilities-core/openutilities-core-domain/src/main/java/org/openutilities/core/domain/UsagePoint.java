package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.openutilities.core.domain.builder.UsagePointBuilder;
import org.openutilities.core.util.RelationsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an usage point.
 */
public class UsagePoint extends Resource implements Serializable, Verifiable, Cacheable<UsagePoint>
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

    @Override
    public UsagePoint cacheableClone()
    {
        UsagePoint clone = UsagePointBuilder.anUsagePoint()
                .id(this.id)
                .code(this.code)
                .specId(this.specId)
                .build();

        for (Relation relation: this.getChannels())
        {
            if (relation.getToResource() instanceof Channel)
            {
                Relation rClone = new Relation(clone, ((Channel) relation.getToResource()).cacheableClone(), relation.getFromDt(), relation.getToDt(), relation.getTypeId());
                clone.getChannels().add(rClone);
            }
        }

        for (Relation relation: this.getMeters())
        {
            if (relation.getToResource() instanceof Meter)
            {
                Relation rClone = new Relation(clone, ((Meter) relation.getToResource()).cacheableClone(), relation.getFromDt(), relation.getToDt(), relation.getTypeId());
                clone.getMeters().add(rClone);
            }
        }

        return clone;
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
