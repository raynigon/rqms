package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.RelevanceTestRun;
import com.raynigon.rqms.domain.offline.valueobjects.*;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RelevanceTestRunAggregate {

    private final RelevanceTestRun root;

    public UUID getId() {
        return root.getId();
    }

    public String getDescription() {
        return root.getDescription();
    }

    public OffsetDateTime getExecution() {
        return root.getExecution();
    }

    public LabelFilter getFilter() {
        return root.getFilter();
    }

    public SearchEngine getSearchEngine() {
        return root.getSearchEngine();
    }

    public Set<TestRunCase> getCases() {
        return Collections.unmodifiableSet(root.getCases());
    }

    public RelevanceScore getMinimumScore() {
        return root.getCases()
                .stream()
                .min(this::relevanceScoreComparator)
                .map(TestRunCase::score)
                .orElse(null);
    }

    public RelevanceScore getAverageScore() {
        double avgScore = root.getCases()
                .stream()
                .mapToDouble(it -> it.score().value())
                .average().orElse(0.0);
        return new RelevanceScore(avgScore);
    }

    public RelevanceScore getMaximumScore() {
        return root.getCases()
                .stream()
                .max(this::relevanceScoreComparator)
                .map(TestRunCase::score)
                .orElse(null);
    }

    public RelevanceScore getMeanReciprocalRank() {
        double score = 0.0;
        for (TestRunCase trCase : root.getCases()) {
            List<ExpectedResult> expected = trCase.expected().stream()
                    .map(it -> new ExpectedResult(it.documentId(), it.relevance(), it.title(), it.imageUrl()))
                    .collect(Collectors.toList());
            List<SearchResult> received = trCase.received().stream()
                    .map(DummySearchResult::new)
                    .collect(Collectors.toList());
            ReciprocalRank metric = new ReciprocalRank(10, expected);
            score += metric.evaluate(received).value();
        }
        return new RelevanceScore(score / root.getCases().size());
    }

    private int relevanceScoreComparator(TestRunCase caseA, TestRunCase caseB) {
        double scoreA = caseA.score().value();
        double scoreB = caseB.score().value();
        return (int) ((scoreA - scoreB) * 100);
    }

    private record DummySearchResult(TestRunSearchResult result) implements SearchResult {

        @NonNull
        @Override
        public String getDocumentId() {
            return result.documentId();
        }

        @NonNull
        @Override
        public String getTitle() {
            return result.title();
        }

        @Override
        public String getImageUrl() {
            return result.imageUrl();
        }
    }
}
