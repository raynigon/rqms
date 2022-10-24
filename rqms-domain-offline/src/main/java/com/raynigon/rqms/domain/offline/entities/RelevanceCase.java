package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.helpers.ExpectedResultsConverter;
import com.raynigon.rqms.domain.offline.helpers.OfflineMetricData;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.infrastructure.converters.LabelConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(schema = "offline", name = "relevance_cases")
@NoArgsConstructor
@AllArgsConstructor
public class RelevanceCase {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "labels")
    @Convert(converter = LabelConverter.class)
    private Map<String, String> labels;

    @Embedded
    private OfflineMetricData metric;

    @Embedded
    private SearchQueryData query;

    @Column(name = "expected_results")
    @Convert(converter = ExpectedResultsConverter.class)
    private List<ExpectedResult> results;
}
