package org.openutilities.proc.core.domain.builder;

import org.openutilities.proc.core.domain.Reading;

import java.math.BigDecimal;
import java.util.Date;

public final class ReadingBuilder
{
    private String meterSerial;
    private Long meterId;
    private String meterChannel;
    private Long channelId;
    private Long typeId;
    private BigDecimal value;
    private Date date;
    private Long usagePointId;

    private ReadingBuilder()
    {
    }

    public static ReadingBuilder aReading()
    {
        return new ReadingBuilder();
    }

    public ReadingBuilder meterSerial(String meterSerial)
    {
        this.meterSerial = meterSerial;
        return this;
    }

    public ReadingBuilder meterId(Long meterId)
    {
        this.meterId = meterId;
        return this;
    }

    public ReadingBuilder meterChannel(String meterChannel)
    {
        this.meterChannel = meterChannel;
        return this;
    }

    public ReadingBuilder channelId(Long channelId)
    {
        this.channelId = channelId;
        return this;
    }

    public ReadingBuilder typeId(Long typeId)
    {
        this.typeId = typeId;
        return this;
    }

    public ReadingBuilder value(BigDecimal value)
    {
        this.value = value;
        return this;
    }

    public ReadingBuilder date(Date date)
    {
        this.date = date;
        return this;
    }

    public ReadingBuilder usagePointId(Long usagePointId)
    {
        this.usagePointId = usagePointId;
        return this;
    }

    public Reading build()
    {
        Reading reading = new Reading();
        reading.setMeterSerial(meterSerial);
        reading.setMeterId(meterId);
        reading.setMeterChannel(meterChannel);
        reading.setChannelId(channelId);
        reading.setTypeId(typeId);
        reading.setValue(value);
        reading.setDate(date);
        reading.setUsagePointId(usagePointId);
        return reading;
    }
}
