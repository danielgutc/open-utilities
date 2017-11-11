package org.openutilities.core.domain.builder;


import org.openutilities.core.domain.Meter;

public final class MeterBuilder
{
    protected Long id;
    protected Long specId;
    protected String code;

    private MeterBuilder()
    {
    }

    public static MeterBuilder aMeter()
    {
        return new MeterBuilder();
    }

    public MeterBuilder id(Long id)
    {
        this.id = id;
        return this;
    }

    public MeterBuilder specId(Long specId)
    {
        this.specId = specId;
        return this;
    }

    public MeterBuilder code(String code)
    {
        this.code = code;
        return this;
    }

    /**
     * Builds the meter.
     * @return
     */
    public Meter build()
    {
        Meter meter = new Meter();
        meter.setId(id);
        meter.setSpecId(specId);
        meter.setCode(code);
        return meter;
    }
}
