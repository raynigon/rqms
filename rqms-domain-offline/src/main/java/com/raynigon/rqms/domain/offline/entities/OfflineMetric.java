package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.List;

public interface OfflineMetric {

    String getName();

    int getCutoff();

    List<ExpectedResult> getExpectedResults();

    RelevanceScore evaluate(List<SearchResult> results);
}
