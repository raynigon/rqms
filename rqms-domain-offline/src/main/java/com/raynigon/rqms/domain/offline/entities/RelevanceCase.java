package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class RelevanceCase {

    private final UUID id;

    private String name;

    private final Map<String, String> labels;

    private OfflineMetric metric;

    private SearchQuery query;

    private Set<ExpectedResult> results;
}
