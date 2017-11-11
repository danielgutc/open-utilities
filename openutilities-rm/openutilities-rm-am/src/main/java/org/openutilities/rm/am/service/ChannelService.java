package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Channel;
import org.openutilities.rm.am.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService
{
    @Autowired
    private ChannelRepository channelRepository;

    public Channel getChannel(String code)
    {
        return null;
    }

    public Channel saveChannel(Channel channel)
    {
        return channelRepository.save(channel);
    }
}
