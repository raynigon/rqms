package com.raynigon.rqms.domain.online.valueobjects;

import java.time.OffsetDateTime;

public record MetricBucket(
        OffsetDateTime start,
        OffsetDateTime end,
        double value
) {
}
