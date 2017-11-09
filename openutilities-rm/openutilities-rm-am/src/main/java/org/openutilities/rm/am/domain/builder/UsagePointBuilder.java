package org.openutilities.rm.am.domain.builder;

import org.openutilities.rm.am.domain.Relation;
import org.openutilities.rm.am.domain.UsagePoint;

import java.util.ArrayList;
import java.util.List;

public final class UsagePointBuilder
{
    protected Long id;
    protected Long specId;
    protected String code;
    private List<Relation> meters = new ArrayList<>();
    private List<Relation> channels = new ArrayList<>();

    private UsagePointBuilder()
    {
    }

    public static UsagePointBuilder anUsagePoint()
    {
        return new UsagePointBuilder();
    }

    public UsagePointBuilder id(Long id)
    {
        this.id = id;
        return this;
    }

    public UsagePointBuilder specId(Long specId)
    {
        this.specId = specId;
        return this;
    }

    public UsagePointBuilder code(String code)
    {
        this.code = code;
        return this;
    }

    public UsagePoint build()
    {
        UsagePoint usagePoint = new UsagePoint();
        usagePoint.setId(id);
        usagePoint.setSpecId(specId);
        usagePoint.setCode(code);
        return usagePoint;
    }
}
