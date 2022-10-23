package com.raynigon.rqms.app.offline.services;

import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.entities.RelevanceCase;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.domain.offline.valueobjects.RecallAtK;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RelevanceCaseOverviewService {

    public List<Pair<RelevanceCaseAggregate, RelevanceScore>> listRelevanceCases() {
        return List.of(
                Pair.of(new RelevanceCaseAggregate(createRoot("Wasser")), new RelevanceScore(1.0)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Kokosnuss")), new RelevanceScore(1.0)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Karotte")), new RelevanceScore(1.0)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.9)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.8)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.7)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.6)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.5)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.4)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.3)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.2)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.1)),
                Pair.of(new RelevanceCaseAggregate(createRoot("Milch")), new RelevanceScore(0.0))
        );
    }

    private RelevanceCase createRoot(String name) {
        List<ExpectedResult> expectedResults = new ArrayList<>();
        return new RelevanceCase(
                UUID.randomUUID(),
                name,
                Map.of("category", "drink", "subcategory", "nut"),
                new RecallAtK(5, expectedResults),
                createQuery(name),
                expectedResults
        );
    }

    private SearchQuery createQuery(String searchTerm) {
        return new SearchQuery() {
            @Override
            public String getType() {
                return "default";
            }

            @Override
            public String getSearchTerm() {
                return searchTerm;
            }

            @Override
            public Map<String, String> getParameters() {
                return Map.of();
            }

            @Override
            public String build() {
                return "-";
            }
        };
    }


}
