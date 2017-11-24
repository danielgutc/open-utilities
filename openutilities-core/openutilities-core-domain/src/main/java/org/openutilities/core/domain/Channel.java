package org.openutilities.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data channel.
 */

public class Channel extends Resource implements Serializable, Verifiable
{
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
