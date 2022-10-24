package com.raynigon.rqms.domain.offline.factories;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.entities.OfflineMetric;
import com.raynigon.rqms.domain.offline.entities.RelevanceCase;
import com.raynigon.rqms.domain.offline.helpers.OfflineMetricData;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.repositories.RelevanceCaseRepository;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelevanceCaseFactory {

    private final RelevanceCaseRepository repository;

    private final OfflineMetricsFactory metricsFactory;

    private final SearchQueryFactory searchQueryFactory;

    public RelevanceCaseAggregate createNewRelevanceCase(
            String name,
            Map<String, String> labels,
            OfflineMetric metric,
            SearchQuery query,
            List<ExpectedResult> results
    ) {
        RelevanceCase root = new RelevanceCase();
        root.setId(UUID.randomUUID());
        root.setName(name);
        root.setLabels(labels);
        root.setMetric(new OfflineMetricData(metric.getName(), metric.getCutoff()));
        root.setQuery(new SearchQueryData(query.getType(), query.getSearchTerm(), query.getParameters()));
        root.setResults(results);
        return toAggregate(repository.save(root));
    }

    public RelevanceCaseAggregate findById(UUID id) {
        return repository.findById(id)
                .map(this::toAggregate)
                .orElse(null);
    }

    public List<RelevanceCaseAggregate> findByLabelFilter(LabelFilter filter) {
        return repository.findAll()
                .stream()
                .map(this::toAggregate)
                .filter(relevanceCase -> filter.matches(relevanceCase.getLabels()))
                .collect(Collectors.toList());
    }

    public List<RelevanceCaseAggregate> listAll() {
        return repository.findAll()
                .stream()
                .map(this::toAggregate)
                .collect(Collectors.toList());
    }

    private RelevanceCaseAggregate toAggregate(RelevanceCase relevanceCase) {
        return new RelevanceCaseAggregate(
                relevanceCase,
                repository,
                metricsFactory,
                searchQueryFactory
        );
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
