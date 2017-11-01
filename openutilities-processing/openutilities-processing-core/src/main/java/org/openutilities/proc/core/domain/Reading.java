package org.openutilities.proc.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reading
{
    private String meterSerial;
    private Long meterId;
    private String meterChannel;
    private Long channelId;
    private Long typeId;
    private BigDecimal value;
    private Date date;
    private Long usagePointId;
}
