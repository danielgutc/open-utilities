package org.openutilities.core.domain.builder;


import org.openutilities.core.domain.Channel;

public final class ChannelBuilder
{
    protected Long id;
    protected Long specId;
    protected String code;

    private ChannelBuilder()
    {
    }

    public static ChannelBuilder aChannel()
    {
        return new ChannelBuilder();
    }

    public ChannelBuilder id(Long id)
    {
        this.id = id;
        return this;
    }

    public ChannelBuilder specId(Long specId)
    {
        this.specId = specId;
        return this;
    }

    public ChannelBuilder code(String code)
    {
        this.code = code;
        return this;
    }

    /**
     * Builds the channel.
     * @return
     */
    public Channel build()
    {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setSpecId(specId);
        channel.setCode(code);
        return channel;
    }
}
