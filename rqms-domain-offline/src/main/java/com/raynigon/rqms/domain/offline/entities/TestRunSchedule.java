package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceTestRunAggregate;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunData;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import org.springframework.scheduling.support.CronExpression;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public record TestRunSchedule(String name,
                              Map<String, String> labels,
                              LabelFilter filter,
                              SearchEngine searchEngine,
                              Set<CronExpression> schedules
) {

    public OffsetDateTime nextRun() {
        OffsetDateTime now = OffsetDateTime.now();
        Optional<OffsetDateTime> result = schedules.stream()
                .map(cron -> cron.next(now))
                .filter(Objects::nonNull)
                .sorted()
                .findFirst();
        if (result.isEmpty()) {
            // TODO replace with proper exception
            throw new RuntimeException("At least one schedule is needed for TestRunSchedule");
        }
        return result.get();
    }

    public RelevanceTestRunAggregate execute(TestRunData data) {
        boolean assertionTestResult = data.getAssertionTests()
                .stream()
                .parallel()
                .allMatch(assertion -> assertion.evaluate(searchEngine.search(assertion.getQuery())));
        if (!assertionTestResult) {
            // TODO replace with proper exception
            throw new RuntimeException("Assertion Tests failed");
        }
        OffsetDateTime execution = OffsetDateTime.now();
        Set<RelevanceCaseAggregate> relevanceCases = data.filterRelevanceCases(filter);
        Set<TestRunCase> testRunCases = relevanceCases.stream()
                .parallel()
                .map(relevanceCase -> {
                    List<SearchResult> results = searchEngine.search(relevanceCase.getQuery());
                    RelevanceScore score = relevanceCase.getMetric().evaluate(results);
                    return TestRunCase.fromRelevanceCase(relevanceCase, results, score);
                })
                .collect(Collectors.toSet());
        RelevanceTestRun entity = new RelevanceTestRun(
                UUID.randomUUID(),
                generateDescription(),
                execution,
                filter,
                searchEngine,
                testRunCases
        );
        return new RelevanceTestRunAggregate(entity);
    }

    private String generateDescription() {
        return "Test Run was executed by schedule: " + name;
    }
}
