package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RelevanceCase {

    private final UUID id;

    private String name;

    private final Map<String, String> labels;

    private OfflineMetric metric;

    private SearchQuery query;

    private List<ExpectedResult> results;
}
