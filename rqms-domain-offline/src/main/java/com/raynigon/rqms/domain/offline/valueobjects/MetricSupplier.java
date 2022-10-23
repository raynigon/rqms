package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;

import java.util.Collections;
import java.util.List;

public interface MetricSupplier {

    default String getMetricName() {
        return supply(2, Collections.emptyList()).getName();
    }

    OfflineMetric supply(int k, List<ExpectedResult> expectedResults);
}
