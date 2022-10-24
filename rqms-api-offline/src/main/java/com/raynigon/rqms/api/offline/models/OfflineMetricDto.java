package com.raynigon.rqms.api.offline.models;

public record OfflineMetricDto(
        String name,
        int cutoff
) {
}
