package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountedCumulativeGain implements OfflineMetric {

    public static final String NAME = "default-discounted-cumulative-gain";

    private final int cutoff;

    private final List<ExpectedResult> expectedResults;


    private final Set<String> expectedResultIds;

    public DiscountedCumulativeGain(int k, List<ExpectedResult> expectedResults) {
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
        if (results.size() > cutoff) {
            results = results.subList(0, cutoff);
        }
        int i = 1;
        double dcg = 0.0;
        for (SearchResult result : results) {
            double gain = isRelevant(result) ? 1.0 : 0.0;
            double discount = 1 / Math.log(i + 1);
            dcg += gain * discount;
            i++;
        }
        return new RelevanceScore(dcg);
    }

    private boolean isRelevant(SearchResult result) {
        return expectedResultIds.contains(result.getDocumentId());
    }
}
