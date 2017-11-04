package org.openutilities.rm.am.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Represents a resource.
 */
@Entity
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable
{
    @Id
    @GeneratedValue
    protected Long id;
    @Column(name = "type_id", updatable = false, insertable = false)
    protected Long typeId;
    @Column(name = "spec_id")
    protected Long specId;
    @Column (nullable = false, unique = true)
    protected String code;

    //<editor-fold desc="Boilerplate code">

    public Resource() {}

    public Resource(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getSpecId()
    {
        return specId;
    }

    public void setSpecId(Long specId)
    {
        this.specId = specId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    //</editor-fold>
}
