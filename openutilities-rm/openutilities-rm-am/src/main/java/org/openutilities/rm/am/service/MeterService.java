package org.openutilities.rm.am.service;

import org.openutilities.rm.am.domain.Meter;
import org.openutilities.rm.am.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Meter service
 */
@Service
public class MeterService
{
    @Autowired
    private MeterRepository meterRepository;

    public Meter getMeter(final String code)
    {
        return meterRepository.findByCode(code);
    }

    public Meter saveMeter(Meter meter)
    {
        return meterRepository.save(meter);
    }
}
