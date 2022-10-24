package com.raynigon.rqms.api.offline.models;

import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record RelevanceCaseDto(
        UUID id,
        String name,
        Map<String, String> labels,
        SearchQueryDto query,

        OfflineMetricDto metric,
        List<ExpectedResult> results
) {
}
