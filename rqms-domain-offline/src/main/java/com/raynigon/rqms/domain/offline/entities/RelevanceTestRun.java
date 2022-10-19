package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import com.raynigon.rqms.infrastructure.search.SearchEngine;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public record RelevanceTestRun(
        UUID id,
        String description,
        OffsetDateTime execution,
        LabelFilter filter,
        SearchEngine searchEngine,
        Set<TestRunCase> cases
) {
}
