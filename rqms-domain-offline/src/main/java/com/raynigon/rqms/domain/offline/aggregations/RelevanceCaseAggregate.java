package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.domain.offline.entities.RelevanceCase;
import com.raynigon.rqms.domain.offline.factories.OfflineMetricsFactory;
import com.raynigon.rqms.domain.offline.helpers.OfflineMetricData;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.helpers.SystemLabels;
import com.raynigon.rqms.domain.offline.repositories.RelevanceCaseRepository;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.domain.offline.valueobjects.Label;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RelevanceCaseAggregate {

    private final RelevanceCase root;

    private final RelevanceCaseRepository repository;

    private final OfflineMetricsFactory metricsFactory;

    private final SearchQueryFactory queryFactory;

    public UUID getId() {
        return root.getId();
    }

    public String getName() {
        return root.getName();
    }

    public void setName(String name) {
        root.setName(name);
        updateSystemLabels();
    }

    public Set<Label> getLabels() {
        return root.getLabels()
                .entrySet()
                .stream()
                .map(entry -> new Label(entry.getKey(), entry.getValue()))
                .collect(Collectors.toUnmodifiableSet());
    }

    public void setLabels(Map<String, String> labels) {
        root.setLabels(new HashMap<>(labels));
        updateSystemLabels();
    }

    public void setLabel(Label label) {
        root.getLabels().put(label.key(), label.value());
        updateSystemLabels();
    }

    public void removeLabel(String key) {
        root.getLabels().remove(key);
        updateSystemLabels();
    }

    public OfflineMetric getMetric() {
        return metricsFactory.create(root.getMetric().getName(), root.getMetric().getCutoff(), root.getResults());
    }

    public void setMetric(String name, int cutoff) {
        // Check this metric is correct
        metricsFactory.create(name, cutoff, root.getResults());
        // Update Values
        root.setMetric(new OfflineMetricData(name, cutoff));
        updateSystemLabels();
    }

    public SearchQuery getQuery() {
        return queryFactory.create(
                root.getQuery().getType(),
                root.getQuery().getSearchTerm(),
                root.getQuery().getParameters()
        );
    }

    public void setQuery(String type, String searchTerm, Map<String, String> parameters) {
        // Check this query is correct
        queryFactory.create(type, searchTerm, parameters);
        // Update Values
        root.setQuery(new SearchQueryData(type, searchTerm, parameters));
        updateSystemLabels();
    }

    public void setExpectedResults(List<ExpectedResult> results) {
        if (results.isEmpty()) {
            throw new IllegalArgumentException("At least one result is needed");
        }
        root.setResults(new ArrayList<>(results));
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

    public List<ExpectedResult> listExpectedResults() {
        return Collections.unmodifiableList(root.getResults());
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

    public void save() {
        repository.save(root);
    }

    private void updateSystemLabels() {
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_TYPE, "relevance-case");
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_ID, root.getId().toString());
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_NAME, root.getName());
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_METRIC, root.getMetric().getName());
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_QUERY, root.getQuery().getType());
        root.getLabels().put(SystemLabels.RELEVANCE_CASE_RESULT_COUNT, String.valueOf(root.getResults().size()));
    }
}
