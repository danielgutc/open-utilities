package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Encapsulate mass cache operations such initial load from database.
 */
@Service
public class CacheLoaderService
{
    @Autowired
    private UsagePointService usagePointService;

    @Autowired
    MeterService meterService;

    @Autowired
    private CacheService cacheService;

    /**
     * Initializes all caches.
     * TODO complete with UP cache.
     *
     */
    public void initCache()
    {
        cacheService.cleanCache(CacheService.METER_CACHE);
        for (Meter meter : meterService.findAll())
        {
            cacheService.addObjectToCache(CacheService.METER_CACHE, meter.getSerialNumber(), meter);
        }
    }
}
