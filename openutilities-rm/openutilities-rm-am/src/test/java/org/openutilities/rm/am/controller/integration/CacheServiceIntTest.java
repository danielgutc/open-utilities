package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.UsagePoint;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.core.domain.builder.UsagePointBuilder;
import org.openutilities.rm.am.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceIntTest
{
    @Autowired
    private CacheService cacheService;

    @Test
    public void addAndGetObject()
    {
        UsagePoint up = UsagePointBuilder.anUsagePoint().specId(1L).code("up-1").build();
        Meter meter = MeterBuilder.aMeter().id(2L).code("mtr-1").build();
        up.getMeters().add(new Relation(up, meter, Relation.UP_TO_METER));

        cacheService.addObjectToCache("usagePoints", up.getCode(), up);

        UsagePoint upCached = cacheService.getObjectFromCache("usagePoints", up.getCode());

        assertEquals("UP not cached properly", upCached.getMeters().size(), 1);
    }
}
