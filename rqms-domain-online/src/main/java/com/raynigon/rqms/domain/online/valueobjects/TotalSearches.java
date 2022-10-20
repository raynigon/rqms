package com.raynigon.rqms.domain.online.valueobjects;

import com.raynigon.rqms.domain.online.entities.SearchFact;

import java.util.Set;

public class TotalSearches implements OnlineMetric {
    @Override
    public String getName() {
        return "default-total-searches";
    }

    @Override
    public double evaluateTotal(Set<SearchFact> searchFacts) {
        return searchFacts.size();
    }
}
