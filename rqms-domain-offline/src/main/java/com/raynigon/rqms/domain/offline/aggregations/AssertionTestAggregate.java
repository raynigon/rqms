package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.AssertionTest;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class AssertionTestAggregate {

    private final AssertionTest root;

    private final SearchQueryFactory searchQueryFactory;

    public UUID getId() {
        return root.getId();
    }

    public SearchQuery getQuery() {
        return searchQueryFactory.create(root.getQuery().getType(), root.getQuery().getSearchTerm(), root.getQuery().getParameters());
    }

    public void addCondition(String code) {
        boolean exists = root.getConditions().stream()
                .anyMatch((AssertionCondition condition) -> condition.code().contentEquals(code));
        if (exists)
            return;
        root.getConditions().add(new AssertionCondition(code));
    }

    public Set<AssertionCondition> listConditions() {
        return Collections.unmodifiableSet(root.getConditions());
    }

    public void removeCondition(String code) {
        Predicate<AssertionCondition> predicate = (AssertionCondition condition) -> condition.code().contentEquals(code);
        if (root.getConditions().stream().noneMatch(predicate)) return;
        if (root.getConditions().size() <= 1) {
            // TODO replace with proper exception
            throw new RuntimeException("Assertion Test needs at least one condition");
        }
        root.getConditions().removeIf(predicate);
    }

    public boolean evaluate(List<SearchResult> results) {
        return root.getConditions()
                .stream()
                .parallel()
                .allMatch(condition -> condition.evaluate(results));
    }
}
