package com.raynigon.rqms.domain.offline.services;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceTestRunAggregate;
import com.raynigon.rqms.domain.offline.entities.RelevanceTestRun;
import com.raynigon.rqms.domain.offline.factories.AssertionTestFactory;
import com.raynigon.rqms.domain.offline.factories.RelevanceCaseFactory;
import com.raynigon.rqms.domain.offline.repositories.TestRunRepository;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelevanceTestRunService {

    private final RelevanceCaseFactory relevanceCaseFactory;
    private final AssertionTestFactory assertionTestFactory;
    private final TestRunRepository repository;

    public RelevanceTestRunAggregate createRun(SearchEngine searchEngine, LabelFilter filter, String description) {
        boolean assertionTestResult = assertionTestFactory.findAll()
                .stream()
                .parallel()
                .allMatch(assertion -> assertion.evaluate(searchEngine.search(assertion.getQuery())));
        if (!assertionTestResult) {
            RelevanceTestRun testRun = new RelevanceTestRun(
                    UUID.randomUUID(),
                    description,
                    OffsetDateTime.now(),
                    true,
                    filter,
                    searchEngine,
                    Set.of()
            );
            return new RelevanceTestRunAggregate(repository.save(testRun));
        }
        OffsetDateTime execution = OffsetDateTime.now();
        List<RelevanceCaseAggregate> relevanceCases = relevanceCaseFactory.findByLabelFilter(filter);
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
                description,
                execution,
                false,
                filter,
                searchEngine,
                testRunCases
        );
        return new RelevanceTestRunAggregate(repository.save(entity));
    }

    public List<RelevanceTestRunAggregate> findAll() {
        return repository.findAll().stream()
                .map(RelevanceTestRunAggregate::new)
                .sorted(this::compareByExecutionDesc)
                .collect(Collectors.toList());
    }

    private int compareByExecutionDesc(RelevanceTestRunAggregate a, RelevanceTestRunAggregate b) {
        return -1 * a.getExecution().compareTo(b.getExecution());
    }

    public RelevanceTestRunAggregate findById(UUID id) {
        return repository.findById(id).map(RelevanceTestRunAggregate::new).orElse(null);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }


}
