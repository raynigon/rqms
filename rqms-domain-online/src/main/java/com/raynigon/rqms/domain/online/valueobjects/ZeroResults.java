package com.raynigon.rqms.domain.online.valueobjects;

import com.raynigon.rqms.domain.online.entities.SearchFact;

import java.util.Set;

public class ZeroResults implements OnlineMetric {
    @Override
    public String getName() {
        return "default-zero-results";
    }

    @Override
    public double evaluateTotal(Set<SearchFact> searchFacts) {
        return 0;
    }
}
