package com.raynigon.rqms.domain.integration.entities;

import com.raynigon.rqms.infrastructure.search.SearchEngine;

import java.util.Map;
import java.util.Set;

public record SearchResultInteraction(
        String searchId,
        String sessionId,
        SearchEngine searchEngine,
        Set<String> testVariants,
        String documentId,
        Long resultRank,
        Map<String, String> parameters
) {
}
