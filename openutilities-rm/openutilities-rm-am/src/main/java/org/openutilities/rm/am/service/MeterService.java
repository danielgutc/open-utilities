package org.openutilities.rm.am.service;

import org.openutilities.core.domain.Meter;
import org.openutilities.rm.am.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Meter service.
 */
@Service
public class MeterService
{
    @Autowired
    private CacheService cacheService;

    @Autowired
    private MeterRepository meterRepository;


    public Meter getMeter(String code, String serialNumber)
    {
        Meter meter =  cacheService.getObjectFromCache(CacheService.METER_CACHE, serialNumber);
        if (meter == null)
        {
            meter = meterRepository.findByCode(code);
            if (meter != null)
            {
                cacheService.addObjectToCache(CacheService.METER_CACHE, meter.getSerialNumber(), meter);
            }
        }

        return meter;
    }

    public Meter saveMeter(Meter meter)
    {
        Meter meterDb =  meterRepository.save(meter);
        cacheService.addObjectToCache(CacheService.METER_CACHE, meter.getSerialNumber(), meterDb);

        return meterDb;
    }

    public Iterable<Meter> findAll()
    {
        return meterRepository.findAll();
    }
}
