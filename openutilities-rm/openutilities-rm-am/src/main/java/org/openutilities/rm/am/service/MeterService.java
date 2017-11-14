package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Channel;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.builder.ChannelBuilder;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.rm.am.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Meter service
 */
@Service
public class MeterService
{
    private static final String CACHE_NAME = "meters";

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MeterRepository meterRepository;

    @PostConstruct
    private void addDevMeter()
    {
        Meter meter = MeterBuilder.aMeter().id(1001L).code("mtr-1").serialNumber("123456").build();
        Channel channel = ChannelBuilder.aChannel().id(1002L).code("ch-1").specId(6001L).build();
        meter.getChannels().add(new Relation(meter, channel, Relation.ANY_TO_CHANNEL));

        saveMeter(meter);
    }

    public Meter getMeter(final String code)
    {
        Meter meter =  meterRepository.findByCode(code);
        if (meter == null)
        {
            meter = meterRepository.findByCode(code);
            cacheService.addObjectToCache(CACHE_NAME, meter.getSerialNumber(), meter);
        }

        return meter;
    }

    public Meter saveMeter(Meter meter)
    {
        Meter meterDb =  meterRepository.save(meter);
        cacheService.addObjectToCache(CACHE_NAME, meter.getSerialNumber(), meterDb);

        return meterDb;
    }
}
