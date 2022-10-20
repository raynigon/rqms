package com.raynigon.rqms.domain.online.valueobjects;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public record SearchQuery(
        OffsetDateTime timestamp,
        String searchTerm,
        Map<String, String> parameters,
        Long offset,
        Long pageSize,
        List<SearchResult> results
) {
}
