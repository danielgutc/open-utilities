package org.openutilities.core.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data channel.
 */
public class Channel extends Resource implements Serializable, Verifiable
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

    //<editor-fold desc="Getters/Setters">

    public List<Relation> getOwner()
    {
        return owner;
    }

    //</editor-fold>
}
