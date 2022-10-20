package com.raynigon.rqms.domain.online.valueobjects;

import com.raynigon.rqms.domain.online.entities.SearchFact;

import java.util.List;
import java.util.Set;

public interface OnlineMetric {

    String getName();

    double evaluateTotal(Set<SearchFact> searchFacts);

    List<MetricBucket> evaluateTimeSeries(Set<SearchFact> searchFacts);
}
