package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.helpers.LabelFilterConverter;
import com.raynigon.rqms.domain.offline.helpers.SearchEngineConverter;
import com.raynigon.rqms.domain.offline.helpers.TestRunCaseConverter;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "offline", name = "relevance_test_runs")
public class RelevanceTestRun {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "execution_timestamp")
    private OffsetDateTime execution;

    @Column(name = "assertions_failed")
    private boolean assertionsFailed;

    @Convert(converter = LabelFilterConverter.class)
    @Column(name = "label_filter")
    private LabelFilter filter;

    @Convert(converter = SearchEngineConverter.class)
    @Column(name = "search_engine")
    private SearchEngine searchEngine;

    @Convert(converter = TestRunCaseConverter.class)
    @Column(name = "test_run_cases")
    private Set<TestRunCase> cases;
}
