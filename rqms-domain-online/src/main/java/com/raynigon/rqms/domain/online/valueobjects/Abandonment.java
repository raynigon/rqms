package com.raynigon.rqms.domain.online.valueobjects;

import com.raynigon.rqms.domain.online.entities.SearchFact;

import java.util.Set;

public class Abandonment implements OnlineMetric {
    @Override
    public String getName() {
        return "default-abandonment";
    }

    @Override
    public double evaluateTotal(Set<SearchFact> searchFacts) {
        return 0;
    }

}
