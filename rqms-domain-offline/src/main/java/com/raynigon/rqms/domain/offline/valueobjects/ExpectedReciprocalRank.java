package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ExpectedReciprocalRank implements OfflineMetric {

    public static final String NAME = "default-expected-reciprocal-rank";


    private final Set<ExpectedResult> expectedResults;

    private final double gradeMax;

    public ExpectedReciprocalRank(Set<ExpectedResult> expectedResults) {
        this.expectedResults = expectedResults;
        this.gradeMax = expectedResults.stream()
                .mapToDouble(ExpectedResult::relevance)
                .max()
                .orElse(0.0);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Set<ExpectedResult> getExpectedResults() {
        return Collections.unmodifiableSet(expectedResults);
    }

    @Override
    public RelevanceScore evaluate(List<SearchResult> results) {
        double p = 1.0;
        double err = 1.0;
        double r = 1;
        for (SearchResult result : results) {
            double R = grade(result);
            err += p * R / r;
            p = p * (1 - R);
            r++;
        }
        return new RelevanceScore(err);
    }

    private double grade(SearchResult result) {
        return expectedResults.stream()
                .filter(it -> it.documentId().equals(result.getDocumentId()))
                .findFirst()
                .map(this::grade)
                .orElse(0.0);
    }

    private double grade(ExpectedResult result) {
        return Math.pow(2, result.relevance()) / Math.pow(2, gradeMax);
    }
}
