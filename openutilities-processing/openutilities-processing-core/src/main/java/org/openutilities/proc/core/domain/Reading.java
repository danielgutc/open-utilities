package org.openutilities.proc.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reading
{
    private String meterSerial;
    private Long meterId;
    private String meterChannel;
    private Long typeId;
    private BigDecimal value;
    private ZonedDateTime date;
    private Long usagePointId;
}
