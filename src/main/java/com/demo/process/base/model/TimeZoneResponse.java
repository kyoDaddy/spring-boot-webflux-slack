package com.demo.process.base.model;

import lombok.Builder;
import lombok.Data;

import java.time.*;

@Data
@Builder
public class TimeZoneResponse {

    private Instant instant;

    private LocalDate localDate;

    private LocalDateTime localDateTime;

    private OffsetDateTime offsetDateTime;

    private OffsetTime offsetTime;

    private ZonedDateTime zonedDateTime;

}
