package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.domain.Meter;
import org.openutilities.rm.am.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeterServiceIntTest
{
    @Autowired
    private MeterService meterService;

    @Test
    public void getMeter()
    {
        Meter meter = meterService.getMeter("mtr-1", null);
        //UsagePoint up = (UsagePoint) meter.getUsagePoints().get(0).getFromResource();
    }
}
