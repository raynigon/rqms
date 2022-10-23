package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecallAtK implements OfflineMetric {

    public static final String NAME = "default-recall-at-k";


    private final int cutoff;

    private final List<ExpectedResult> expectedResults;

    private final Set<String> expectedResultIds;

    public RecallAtK(int k, List<ExpectedResult> expectedResults) {
        if (k <= 1) {
            throw new RuntimeException("cutoff has to be greater than 1");
        }
        this.cutoff = k;
        this.expectedResults = expectedResults;
        this.expectedResultIds = expectedResults.stream()
                .map(ExpectedResult::documentId)
                .collect(Collectors.toSet());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<ExpectedResult> getExpectedResults() {
        return Collections.unmodifiableList(expectedResults);
    }

    @Override
    public RelevanceScore evaluate(List<SearchResult> results) {
        int i = 0;
        double dividend = 0.0;
        double divisor = 0.0;
        for (SearchResult result : results) {
            boolean relevant = isRelevant(result);
            if (i <= cutoff) {
                dividend += relevant ? 1.0 : 0.0;
            }
            divisor += relevant ? 1.0 : 0.0;
        }
        if (divisor <= 0.0) {
            return new RelevanceScore(0.0);
        }
        return new RelevanceScore(dividend / divisor);
    }

    private boolean isRelevant(SearchResult result) {
        return expectedResultIds.contains(result.getDocumentId());
    }
}
