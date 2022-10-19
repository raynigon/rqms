package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.domain.offline.aggregations.AssertionTestAggregate;
import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TestRunData {

    private final Set<AssertionTestAggregate> assertionTests;

    private final Set<RelevanceCaseAggregate> relevanceCases;

    public Set<AssertionTestAggregate> getAssertionTests() {
        return Collections.unmodifiableSet(assertionTests);
    }

    public Set<RelevanceCaseAggregate> filterRelevanceCases(LabelFilter filter) {
        return relevanceCases.stream()
                .filter(relevanceCase -> filter.matches(relevanceCase.getLabels()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
