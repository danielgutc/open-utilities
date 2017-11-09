package org.openutilities.rm.am.controller.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openutilities.rm.am.domain.Channel;
import org.openutilities.rm.am.domain.Relation;
import org.openutilities.rm.am.domain.UsagePoint;
import org.openutilities.rm.am.domain.Meter;
import org.openutilities.rm.am.domain.builder.ChannelBuilder;
import org.openutilities.rm.am.domain.builder.MeterBuilder;
import org.openutilities.rm.am.domain.builder.UsagePointBuilder;
import org.openutilities.rm.am.service.ChannelService;
import org.openutilities.rm.am.service.MeterService;
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

    @Autowired
    private MeterService meterService;

    @Autowired
    private ChannelService channelService;

    @Test
    public void getUsagePoint()
    {
        //UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-1").build();
        UsagePoint up = usagePointService.getUsagePoint("up-1");
        //Meter meter = (Meter) up.getMeters().get(0).getToResource();
    }

    @Test
    public void saveUsagePoint()
    {
        // Elements to relate
        UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-3").specId(1002L).build();
        Meter meter = MeterBuilder.aMeter().id(2L).specId(2002L).code("mtr-2").build();
        //meterService.saveMeter(meter);
        Channel sourceChannel = ChannelBuilder.aChannel().id(3L).specId(6002L).code("dch-4").build();
        //channelService.saveChannel(sourceChannel);
        Channel derivedChannel = ChannelBuilder.aChannel().id(4L).specId(7002L).code("dch-5").build();
        //channelService.saveChannel(derivedChannel);

        up.getMeters().add(new Relation(up, meter, Relation.UP_TO_METER)); // UP -> meter relation
        up.getChannels().add(new Relation(up, derivedChannel, Relation.ANY_TO_CHANNEL)); // UP -> channel relation
        meter.getChannels().add(new Relation(meter, sourceChannel, Relation.ANY_TO_CHANNEL));

        usagePointService.saveUsagePoint(up);

        UsagePoint upDb = usagePointService.getUsagePoint("up-3");
        upDb.getChannels().get(0);
    }
}
