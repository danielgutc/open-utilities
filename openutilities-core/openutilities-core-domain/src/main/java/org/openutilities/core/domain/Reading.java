package org.openutilities.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Reading implements Serializable
{
    private String meterSerial;
    private Long meterId;
    private String meterChannel;
    private Long channelId;
    private Long typeId;
    private BigDecimal value;
    private Date date;
    private Long usagePointId;
    private Integer status;
    private Long version;

    //<editor-fold desc="Getters/Setters">

    public String getMeterSerial()
    {
        return meterSerial;
    }

    public void setMeterSerial(String meterSerial)
    {
        this.meterSerial = meterSerial;
    }

    public Long getMeterId()
    {
        return meterId;
    }

    public void setMeterId(Long meterId)
    {
        this.meterId = meterId;
    }

    public String getMeterChannel()
    {
        return meterChannel;
    }

    public void setMeterChannel(String meterChannel)
    {
        this.meterChannel = meterChannel;
    }

    public Long getChannelId()
    {
        return channelId;
    }

    public void setChannelId(Long channelId)
    {
        this.channelId = channelId;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public void setValue(BigDecimal value)
    {
        this.value = value;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Long getUsagePointId()
    {
        return usagePointId;
    }

    public void setUsagePointId(Long usagePointId)
    {
        this.usagePointId = usagePointId;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }

    //</editor-fold>

}
