package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.rm.am.domain.UsagePoint;
import org.openutilities.rm.am.service.UsagePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsagePointServiceIntTest
{
    @Autowired
    private UsagePointService usagePointService;

    @Test
    public void getUsagePoint()
    {
        //UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-1").build();
        UsagePoint up = usagePointService.getUsagePoint("up-1");
    }
}
