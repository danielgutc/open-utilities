package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.openutilities.core.domain.builder.ChannelBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data channel.
 */
public class Channel extends Resource implements Serializable, Verifiable, Cacheable<Channel>
{
    @JsonBackReference
    private List<Relation> owner = new ArrayList<>();

    public Channel()
    {
        super(3L);
    }

    @Override
    public void verify()
    {
        super.verify();
    }

    @Override
    public Channel cacheableClone()
    {
        return ChannelBuilder.aChannel().id(this.id).code(this.code).specId(this.specId).build();
    }

    //<editor-fold desc="Getters/Setters">

    public List<Relation> getOwner()
    {
        return owner;
    }

    //</editor-fold>
}
