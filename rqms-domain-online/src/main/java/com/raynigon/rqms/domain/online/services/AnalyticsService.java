package com.raynigon.rqms.domain.online.services;

import com.raynigon.rqms.domain.online.entities.ABTest;
import com.raynigon.rqms.domain.online.valueobjects.MetricBucket;
import com.raynigon.rqms.domain.online.valueobjects.TimeInterval;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Map;

public class AnalyticsService {

    public Map<String, Double> evaluateTotal(ABTest abTest, String metric) {
        throw new NotImplementedException("Not implemented yet");
    }

    public Double evaluateTotal(SearchEngine searchEngine, String metric) {
        throw new NotImplementedException("Not implemented yet");
    }

    public Map<String, Double> evaluateTimeFrame(ABTest abTest, String metric, TimeInterval interval) {
        throw new NotImplementedException("Not implemented yet");
    }

    public Double evaluateTimeFrame(SearchEngine searchEngine, String metric, TimeInterval interval) {
        throw new NotImplementedException("Not implemented yet");
    }

    public Map<String, List<MetricBucket>> evaluateTimeSeries(ABTest abTest, String metric) {
        throw new NotImplementedException("Not implemented yet");
    }

    public List<MetricBucket> evaluateTimeSeries(SearchEngine searchEngine, String metric) {
        throw new NotImplementedException("Not implemented yet");
    }
}
