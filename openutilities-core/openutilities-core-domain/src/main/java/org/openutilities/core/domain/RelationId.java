package org.openutilities.core.domain;

import java.io.Serializable;
import java.util.Date;

public class RelationId implements Serializable
{
    private Resource fromResource;
    private Resource toResource;
    private Date fromDt;
}
