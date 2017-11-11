package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.core.domain.Channel;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.UsagePoint;
import org.openutilities.core.domain.builder.ChannelBuilder;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.core.domain.builder.UsagePointBuilder;
import org.openutilities.rm.am.service.UsagePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsagePointServiceIntTest
{
    @Autowired
    private UsagePointService usagePointService;

    @Test
    public void saveUsagePoint()
    {
        // Elements to relate
        UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-3").specId(1002L).build();
        Meter meter = MeterBuilder.aMeter().id(2L).specId(2002L).code("mtr-2").build();
        Channel sourceChannel = ChannelBuilder.aChannel().id(3L).specId(6002L).code("dch-4").build();
        Channel derivedChannel = ChannelBuilder.aChannel().id(4L).specId(7002L).code("dch-5").build();

        up.getMeters().add(new Relation(up, meter, Relation.UP_TO_METER)); // UP -> meter relation
        up.getChannels().add(new Relation(up, derivedChannel, Relation.ANY_TO_CHANNEL)); // UP -> channel relation
        meter.getChannels().add(new Relation(meter, sourceChannel, Relation.ANY_TO_CHANNEL));

        usagePointService.saveUsagePoint(up);

        UsagePoint upDb = usagePointService.getUsagePoint("up-3");
        assertEquals("Error in number of channels", upDb.getChannels().size(), 1);
        assertEquals("Error in number of meters", upDb.getMeters().size(), 1);
    }
}
