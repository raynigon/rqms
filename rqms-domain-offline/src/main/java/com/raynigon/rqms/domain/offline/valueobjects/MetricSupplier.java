package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;

import java.util.Collections;
import java.util.Set;

public interface MetricSupplier {

    default String getMetricName() {
        return supply(2, Collections.emptySet()).getName();
    }

    OfflineMetric supply(int k, Set<ExpectedResult> expectedResults);
}
