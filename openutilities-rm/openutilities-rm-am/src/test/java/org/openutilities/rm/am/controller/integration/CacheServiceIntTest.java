package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.UsagePoint;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.core.domain.builder.UsagePointBuilder;
import org.openutilities.rm.am.App;
import org.openutilities.rm.am.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {App.class, HazelcastConfiguration.class})
public class CacheServiceIntTest
{
    private static final String CACHE_NAME = "usagePoints";

    @Autowired
    private CacheService cacheService;

    @Test
    public void addAndGetObject()
    {
        UsagePoint up = UsagePointBuilder.anUsagePoint().specId(1L).code("up-1").build();
        Meter meter = MeterBuilder.aMeter().id(2L).code("mtr-1").build();
        up.getMeters().add(new Relation(up, meter, new Date(), null, Relation.UP_TO_METER));

        cacheService.addObjectToCache(CACHE_NAME, up.getCode(), up);

        UsagePoint upCached = cacheService.getObjectFromCache(CACHE_NAME, up.getCode());

        assertEquals("UP not cached properly", upCached.getMeters().size(), 1);
    }
}
