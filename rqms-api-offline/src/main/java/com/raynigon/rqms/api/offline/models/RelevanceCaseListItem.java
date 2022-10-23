package com.raynigon.rqms.api.offline.models;

import java.util.Map;
import java.util.UUID;

public record RelevanceCaseListItem(
        UUID id,
        String name,
        Map<String, String> labels,
        double relevanceScore,
        long resultCount
) {
}
