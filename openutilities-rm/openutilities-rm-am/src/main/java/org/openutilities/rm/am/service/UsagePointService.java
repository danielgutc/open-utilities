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
    private static final String CACHE_NAME = "usagePoints";

    @Autowired
    private UsagePointRepository usagePointRepository;

    @Autowired
    private CacheService cacheService;

    public UsagePoint getUsagePoint(String code)
    {
        UsagePoint up = cacheService.getObjectFromCache(CACHE_NAME, code);
        if (up == null)
        {
            up = usagePointRepository.findByCode(code);
            cacheService.addObjectToCache(CACHE_NAME, up.getCode(), up);
        }

        return up;
    }

    public UsagePoint saveUsagePoint(UsagePoint up)
    {
        UsagePoint upDb = usagePointRepository.save(up);
        cacheService.addObjectToCache(CACHE_NAME, up.getCode(), up);

        return upDb;
    }
}
