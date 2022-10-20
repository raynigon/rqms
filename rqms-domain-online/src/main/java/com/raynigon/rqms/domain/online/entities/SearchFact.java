package com.raynigon.rqms.domain.online.entities;

import com.raynigon.rqms.domain.online.valueobjects.SearchQuery;
import com.raynigon.rqms.domain.online.valueobjects.SearchResultInteraction;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public record SearchFact(
        String searchId,
        String sessionId,
        Long totalResults,
        Set<TestVariant> variants,
        SearchEngine searchEngine,
        Set<SearchQuery> queries,
        Set<SearchResultInteraction> interactions
) {

    public SearchFact {
        if (queries.isEmpty()) {
            // TODO replace with proper exception
            throw new RuntimeException("At least one query is needed");
        }
    }
}
