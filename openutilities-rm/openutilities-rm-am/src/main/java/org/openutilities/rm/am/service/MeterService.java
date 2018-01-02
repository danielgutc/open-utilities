package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Meter;
import org.openutilities.rm.am.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Meter getMeter(final String code)
    {
        Meter meter =  meterRepository.findByCode(code);
        if (meter == null)
        {
            meter = meterRepository.findByCode(code);
            if (meter != null)
            {
                cacheService.addObjectToCache(CACHE_NAME, meter.getSerialNumber(), meter);
            }
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
