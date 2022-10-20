package com.raynigon.rqms.domain.online.valueobjects;

import java.time.OffsetDateTime;

public record TimeInterval(
        OffsetDateTime from,
        OffsetDateTime to
) {
    public TimeInterval {
        if (!from.isBefore(to)) {
            throw new IllegalArgumentException("From has to be before to");
        }
    }

    public static TimeInterval lastHour() {
        OffsetDateTime now = OffsetDateTime.now();
        return new TimeInterval(now.minusHours(1), now);
    }

    public static TimeInterval lastDay() {
        OffsetDateTime now = OffsetDateTime.now();
        return new TimeInterval(now.minusDays(1), now);
    }

    public static TimeInterval last7Days() {
        OffsetDateTime now = OffsetDateTime.now();
        return new TimeInterval(now.minusDays(7), now);
    }

    public static TimeInterval last30Days() {
        OffsetDateTime now = OffsetDateTime.now();
        return new TimeInterval(now.minusDays(30), now);
    }
}
