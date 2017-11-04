package org.openutilities.rm.am.domain.builder;

import org.openutilities.rm.am.domain.Resource;

public final class ResourceBuilder
{
    protected Long id;
    protected Long typeId;
    protected Long specId;
    protected String code;

    private ResourceBuilder()
    {
    }

    public static ResourceBuilder aResource()
    {
        return new ResourceBuilder();
    }

    public ResourceBuilder id(Long id)
    {
        this.id = id;
        return this;
    }

    public ResourceBuilder typeId(Long typeId)
    {
        this.typeId = typeId;
        return this;
    }

    public ResourceBuilder specId(Long specId)
    {
        this.specId = specId;
        return this;
    }

    public ResourceBuilder code(String code)
    {
        this.code = code;
        return this;
    }

    public Resource build()
    {
        Resource resource = new Resource();
        resource.setId(id);
        resource.setTypeId(typeId);
        resource.setSpecId(specId);
        resource.setCode(code);
        return resource;
    }
}
