package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.AssertionTest;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.repositories.AssertionTestRepository;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import com.raynigon.rqms.infrastructure.scripting.ScriptEvaluator;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AssertionTestAggregate {

    private final AssertionTest root;

    private final AssertionTestRepository repository;

    private final SearchQueryFactory searchQueryFactory;

    private final ScriptEvaluator scriptEvaluator;

    public UUID getId() {
        return root.getId();
    }

    public SearchQuery getQuery() {
        return searchQueryFactory.create(root.getQuery().getType(), root.getQuery().getSearchTerm(), root.getQuery().getParameters());
    }

    public void setQuery(String type, String searchTerm, Map<String, String> parameters) {
        root.setQuery(new SearchQueryData(type, searchTerm, parameters));
    }

    public void setConditions(List<String> conditions) {
        root.setConditions(conditions.stream().map(AssertionCondition::new).collect(Collectors.toSet()));
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
                .allMatch(condition -> condition.evaluate(scriptEvaluator, results));
    }

    public void save() {
        repository.save(root);
    }
}
