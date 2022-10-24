package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceTestRunAggregate;
import com.raynigon.rqms.domain.offline.services.RelevanceTestRunService;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import org.springframework.scheduling.support.CronExpression;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public record TestRunSchedule(String name,
                              Map<String, String> labels,
                              LabelFilter filter,
                              SearchEngine searchEngine,
                              Set<CronExpression> schedules
) {

    public OffsetDateTime nextRun() {
        OffsetDateTime now = OffsetDateTime.now();
        Optional<OffsetDateTime> result = schedules.stream()
                .map(cron -> cron.next(now))
                .filter(Objects::nonNull)
                .sorted()
                .findFirst();
        if (result.isEmpty()) {
            // TODO replace with proper exception
            throw new RuntimeException("At least one schedule is needed for TestRunSchedule");
        }
        return result.get();
    }

    public RelevanceTestRunAggregate execute(RelevanceTestRunService service) {
        return service.createRun(searchEngine, filter, generateDescription());
    }

    private String generateDescription() {
        return "Test Run was executed by schedule: " + name;
    }
}
