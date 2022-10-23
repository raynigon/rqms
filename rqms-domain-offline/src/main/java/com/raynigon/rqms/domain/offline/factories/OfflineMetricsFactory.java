package com.raynigon.rqms.domain.offline.factories;

import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.domain.offline.valueobjects.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfflineMetricsFactory {

    private final Map<String, MetricSupplier> suppliers = new HashMap<>();

    public OfflineMetricsFactory() {
        // Add default metrics
        suppliers.put(PrecisionAtK.NAME, PrecisionAtK::new);
        suppliers.put(RecallAtK.NAME, RecallAtK::new);
        suppliers.put(ReciprocalRank.NAME, ReciprocalRank::new);
        suppliers.put(DiscountedCumulativeGain.NAME, DiscountedCumulativeGain::new);
        suppliers.put(ExpectedReciprocalRank.NAME, (_k, results) -> new ExpectedReciprocalRank(results));
    }

    public OfflineMetricsFactory(Set<MetricSupplier> suppliers) {
        this();
        this.suppliers.putAll(suppliers.stream()
                .collect(Collectors.toMap(MetricSupplier::getMetricName, Function.identity())));
    }

    public Set<String> listMetrics() {
        return suppliers.keySet();
    }

    public OfflineMetric create(String name, int k, List<ExpectedResult> expectedResults) {
        if (!suppliers.containsKey(name)) {
            throw new RuntimeException("Unknown Metric");
        }
        return suppliers.get(name).supply(k, expectedResults);
    }

    public void registerSupplier(MetricSupplier supplier) {
        String metricName = supplier.getMetricName();
        if (metricName.toLowerCase().startsWith("default-")) {
            // TODO replace with proper exception
            throw new RuntimeException("Default Metrics cannot be overriden");
        }
        suppliers.put(metricName, supplier);
    }
}
