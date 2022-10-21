package com.raynigon.rqms.domain.integration.entities;

import com.raynigon.rqms.infrastructure.search.SearchEngine;

import java.util.Map;
import java.util.Set;

public record SearchLogEntry(
        String searchId,
        String sessionId,
        SearchEngine searchEngine,
        Set<String> testVariants,
        String searchTerm,
        Map<String, String> parameters,
        Long offset,
        Long pageSize
) {
}
