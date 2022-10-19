package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.entities.RelevanceTestRun;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class RelevanceTestRunAggregate {

    private final RelevanceTestRun root;

    public UUID getId() {
        return root.id();
    }

    public String getDescription() {
        return root.description();
    }

    public OffsetDateTime getExecution() {
        return root.execution();
    }

    public LabelFilter getFilter() {
        return root.filter();
    }

    public SearchEngine getSearchEngine() {
        return root.searchEngine();
    }

    Set<TestRunCase> listCases() {
        return Collections.unmodifiableSet(root.cases());
    }


    // TODO implement
}
