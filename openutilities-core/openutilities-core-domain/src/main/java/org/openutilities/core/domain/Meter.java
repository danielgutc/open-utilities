package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.core.exceptions.DomainRuleException;
import org.openutilities.core.util.RelationsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent a meter device.
 */
public class Meter extends Resource implements Serializable, Verifiable, Cacheable<Meter>
{
    private String serialNumber;
    @JsonBackReference
    private List<Relation> usagePoints = new ArrayList<>();
    @JsonManagedReference
    private List<Relation> channels = new ArrayList<>();

    public Meter()
    {
        super(2L);
    }

    @Override
    public void verify()
    {
        // Run parent verifications
        super.verify();

        // Run meter specific verifications
        if (serialNumber == null)
        {
            throw new DomainRuleException(this, "Serial number cannot be null");
        }

        //Verify children relations
        RelationsUtils.verifyRelations(channels, this);

        // Run children entities verification
        channels.stream().forEach(cr -> ((Channel)cr.getToResource()).verify());
    }

    /**
     * Clone the object.
     * @return
     */
    @Override
    public Meter cacheableClone()
    {
        Meter clone = MeterBuilder.aMeter()
                .code(this.code)
                .id(this.id)
                .specId(this.specId)
                .serialNumber(this.serialNumber).build();

        for (Relation relation: this.getChannels())
        {
            if (relation.getToResource() instanceof  Channel) // TODO Fix ORM mapping to avoid this nasty code
            {
                Relation rClone = new Relation(clone, ((Channel) relation.getToResource()).cacheableClone(), relation.getFromDt(), relation.getToDt(), relation.getTypeId());
                clone.getChannels().add(rClone);
            }
        }

        /*for (Relation relation: this.getUsagePoints())
        {
            Relation rClone = new Relation(((UsagePoint)relation.getFromResource()).cacheableClone(), clone, relation.getFromDt(), relation.getToDt(), relation.getTypeId());
            clone.getUsagePoints().add(rClone);
        }*/

        return clone;
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
