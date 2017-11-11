package org.openutilities.rm.am.service;

import org.openutilities.core.domain.UsagePoint;
import org.openutilities.rm.am.repository.UsagePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Usage point service.
 */
@Service
public class UsagePointService
{
    @Autowired
    private UsagePointRepository usagePointRepository;

    public UsagePoint getUsagePoint(String code)
    {
        return usagePointRepository.findByCode(code);
    }

    public UsagePoint saveUsagePoint(UsagePoint up)
    {
        return usagePointRepository.save(up);
    }
}
