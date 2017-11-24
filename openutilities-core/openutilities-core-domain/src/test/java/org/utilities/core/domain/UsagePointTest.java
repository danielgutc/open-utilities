package org.utilities.core.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openutilities.core.domain.Channel;
import org.openutilities.core.domain.Meter;
import org.openutilities.core.domain.Relation;
import org.openutilities.core.domain.UsagePoint;
import org.openutilities.core.domain.builder.ChannelBuilder;
import org.openutilities.core.domain.builder.MeterBuilder;
import org.openutilities.core.domain.builder.UsagePointBuilder;
import org.openutilities.core.exceptions.DomainRuleException;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UsagePointTest
{
    @Test
    public void UsagePointVerifyPositive()
    {
        UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-3").specId(1002L).build();
        Meter meter = MeterBuilder.aMeter().id(2L).specId(2002L).code("mtr-2").serialNumber("123456").build();
        Channel sourceChannel = ChannelBuilder.aChannel().id(3L).specId(6002L).code("dch-4").build();
        Channel derivedChannel = ChannelBuilder.aChannel().id(4L).specId(7002L).code("dch-5").build();

        Date fromDt = new Date();
        up.getMeters().add(new Relation(up, meter, fromDt, null, Relation.UP_TO_METER)); // UP -> meter relation
        up.getChannels().add(new Relation(up, derivedChannel, fromDt, null, Relation.ANY_TO_CHANNEL)); // UP -> channel relation
        meter.getChannels().add(new Relation(meter, sourceChannel, fromDt, null, Relation.ANY_TO_CHANNEL));

        up.verify();
    }

    @Test(expected = DomainRuleException.class)
    public void UsagePointVerifyNegative()
    {
        UsagePoint up = UsagePointBuilder.anUsagePoint().id(1L).code("up-3").specId(1002L).build();
        Meter meter = MeterBuilder.aMeter().id(2L).specId(2002L).code("mtr-2").serialNumber("123456").build();
        Channel sourceChannel1 = ChannelBuilder.aChannel().id(3L).specId(6001L).code("dch-4").build();
        Channel sourceChannel2 = ChannelBuilder.aChannel().id(4L).specId(6001L).code("dch-5").build();

        Date fromDt = new Date();
        up.getMeters().add(new Relation(up, meter, fromDt, null, Relation.UP_TO_METER)); // UP -> meter relation
        meter.getChannels().add(new Relation(meter, sourceChannel1, fromDt, null, Relation.ANY_TO_CHANNEL)); // meter -> channel relation
        meter.getChannels().add(new Relation(meter, sourceChannel2, fromDt, null, Relation.ANY_TO_CHANNEL)); // meter -> channel relation

        up.verify();
    }
}
