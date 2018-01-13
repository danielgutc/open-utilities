package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.openutilities.core.exceptions.DomainRuleException;
import org.openutilities.core.util.RelationsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent a meter device.
 */
public class Meter extends Resource implements Serializable, Verifiable
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
