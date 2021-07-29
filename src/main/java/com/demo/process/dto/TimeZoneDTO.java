package com.demo.process.dto;

import lombok.Builder;
import lombok.Data;

import java.time.*;

@Data
@Builder
public class TimeZoneDTO {

    private Instant instant;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private OffsetDateTime offsetDateTime;

    private OffsetTime offsetTime;

    private ZonedDateTime zonedDateTime;

}
