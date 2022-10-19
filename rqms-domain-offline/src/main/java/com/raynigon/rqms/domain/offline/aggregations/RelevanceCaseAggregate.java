package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.domain.offline.entities.RelevanceCase;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.domain.offline.valueobjects.Label;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RelevanceCaseAggregate {

    private final RelevanceCase root;

    public UUID getId() {
        return root.getId();
    }

    public String getName() {
        return root.getName();
    }

    public Set<Label> getLabels() {
        return root.getLabels()
                .entrySet()
                .stream()
                .map(entry -> new Label(entry.getKey(), entry.getValue()))
                .collect(Collectors.toUnmodifiableSet());
    }

    public void setLabel(Label label) {
        root.getLabels().put(label.key(), label.value());
    }

    public void removeLabel(String key) {
        root.getLabels().remove(key);
    }

    public OfflineMetric getMetric() {
        return root.getMetric();
    }

    public SearchQuery getQuery() {
        return root.getQuery();
    }

    public void addExpectedResult(SearchResult result) {
        boolean exists = root.getResults().stream().anyMatch(expectedResult -> expectedResult.documentId().equals(result.getDocumentId()));
        if (exists) {
            // TODO check if an exception should be thrown here?
            return;
        }
        root.getResults().add(new ExpectedResult(
                result.getDocumentId(),
                0.0,
                result.getTitle(),
                result.getImageUrl()
        ));
    }

    public void removeExpectedResult(String documentId) {
        Predicate<ExpectedResult> predicate = (ExpectedResult expectedResult) -> expectedResult.documentId().equals(documentId);
        if (root.getResults().stream().noneMatch(predicate)) return;
        if (root.getResults().size() <= 1) {
            // TODO replace with proper exception
            throw new RuntimeException("Relevance Case needs at least one result");
        }
    }

    public Set<ExpectedResult> listExpectedResults() {
        return Collections.unmodifiableSet(root.getResults());
    }

    public void setResultRelevance(String documentId, double relevance) {
        if (relevance <= 0.0 || relevance >= 1.0) {
            // TODO replace with proper exception
            throw new RuntimeException("The relevance has to be in the interval between zero and one inclusive");
        }
        Predicate<ExpectedResult> predicate = (ExpectedResult expectedResult) -> expectedResult.documentId().equals(documentId);
        Optional<ExpectedResult> expectedResult = root.getResults().stream().filter(predicate).findFirst();
        if (expectedResult.isEmpty()) {
            // TODO replace with proper exception
            throw new RuntimeException("Expected Result not found");
        }
        ExpectedResult resultWithRelevance = expectedResult.get().withRelevance(relevance);
        root.getResults().removeIf(predicate);
        root.getResults().add(resultWithRelevance);
    }
}
