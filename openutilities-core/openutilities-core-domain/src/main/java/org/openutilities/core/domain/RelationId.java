package org.openutilities.core.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RelationId implements Serializable
{
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Resource fromResource;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Resource toResource;
}
