package org.openutilities.rm.am.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/*
Represents a data channel.
 */
@Entity(name = "Channel")
@DiscriminatorValue("3")
public class Channel extends Resource implements Serializable
{
    //<editor-fold desc="Boilerplate code">

    public Channel()
    {
        super(3L);
    }

    //</editor-fold>
}
