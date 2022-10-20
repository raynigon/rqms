package com.raynigon.rqms.domain.online.valueobjects;

public record MetricBucket(
        TimeInterval interval,
        double value
) {
}
