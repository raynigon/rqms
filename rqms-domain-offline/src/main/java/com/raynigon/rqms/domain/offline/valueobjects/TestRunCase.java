package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record TestRunCase(
        UUID id,
        String name,
        RelevanceScore score,
        Map<String, String> labels,
        Set<TestRunSearchResult> expected,
        Set<TestRunSearchResult> received
) {

    public static TestRunCase fromRelevanceCase(
            RelevanceCaseAggregate relevanceCase,
            List<SearchResult> received,
            RelevanceScore score
    ) {
        Map<String, String> labels = relevanceCase.getLabels()
                .stream()
                .collect(Collectors.toMap(Label::key, Label::value));
        Set<TestRunSearchResult> expectedResults = relevanceCase.listExpectedResults().stream()
                .map(it -> new TestRunSearchResult(
                        it.documentId(),
                        it.relevance(),
                        it.title(),
                        it.imageUrl()))
                .collect(Collectors.toSet());
        Set<TestRunSearchResult> receivedResults = received.stream()
                .map(it -> new TestRunSearchResult(
                        it.getDocumentId(),
                        0.0,
                        it.getTitle(),
                        it.getImageUrl()))
                .collect(Collectors.toSet());
        return new TestRunCase(
                UUID.randomUUID(),
                relevanceCase.getName(),
                score,
                labels,
                expectedResults,
                receivedResults
        );
    }
}
