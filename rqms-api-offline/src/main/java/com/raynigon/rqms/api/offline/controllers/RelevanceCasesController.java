package com.raynigon.rqms.api.offline.controllers;

import com.raynigon.rqms.api.offline.mappers.RelevanceCaseMapper;
import com.raynigon.rqms.api.offline.models.RelevanceCaseDto;
import com.raynigon.rqms.api.offline.models.RelevanceCaseListItem;
import com.raynigon.rqms.app.offline.services.RelevanceCaseOverviewService;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.factories.OfflineMetricsFactory;
import com.raynigon.rqms.domain.offline.factories.RelevanceCaseFactory;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offline/relevance-cases")
public class RelevanceCasesController {

    private final RelevanceCaseMapper mapper;

    private final RelevanceCaseOverviewService service;

    private final RelevanceCaseFactory factory;

    private final OfflineMetricsFactory metricsFactory;

    private final SearchQueryFactory queryFactory;

    @GetMapping
    List<RelevanceCaseListItem> listRelevanceCases() {
        return service.listRelevanceCases()
                .stream()
                .map(it -> mapper.mapToRelevanceCaseListItem(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    RelevanceCaseDto getRelevanceCase(@PathVariable("id") UUID id) {
        RelevanceCaseAggregate aggregate = factory.findById(id);
        if (aggregate == null) {
            throw new RuntimeException("Relevance Case not found");
        }
        return mapper.mapToRelevanceCaseDto(aggregate);
    }

    @PostMapping
    RelevanceCaseDto createRelevanceCase(@RequestBody RelevanceCaseDto body) {
        RelevanceCaseAggregate aggregate = factory.createNewRelevanceCase(
                body.name(),
                body.labels(),
                metricsFactory.create(body.metric().name(), body.metric().cutoff(), body.results()),
                queryFactory.create(body.query().type(), body.query().searchTerm(), body.query().parameters()),
                body.results()
        );
        return mapper.mapToRelevanceCaseDto(aggregate);
    }

    @PutMapping("{id}")
    RelevanceCaseDto updateRelevanceCase(@PathVariable("id") UUID id, @RequestBody RelevanceCaseDto body) {
        RelevanceCaseAggregate aggregate = factory.findById(id);
        aggregate.setName(body.name());
        aggregate.setLabels(body.labels());
        aggregate.setMetric(body.metric().name(), body.metric().cutoff());
        aggregate.setQuery(body.query().type(), body.query().searchTerm(), body.query().parameters());
        aggregate.setExpectedResults(body.results());
        aggregate.save();
        return mapper.mapToRelevanceCaseDto(aggregate);
    }

    @DeleteMapping("{id}")
    void deleteRelevanceCase(@PathVariable("id") UUID id) {
        factory.deleteById(id);
    }
}
