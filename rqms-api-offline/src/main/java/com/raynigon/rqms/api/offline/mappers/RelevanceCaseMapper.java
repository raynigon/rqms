package com.raynigon.rqms.api.offline.mappers;

import com.raynigon.rqms.api.offline.models.OfflineMetricDto;
import com.raynigon.rqms.api.offline.models.RelevanceCaseDto;
import com.raynigon.rqms.api.offline.models.RelevanceCaseListItem;
import com.raynigon.rqms.api.offline.models.SearchQueryDto;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.valueobjects.Label;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RelevanceCaseMapper {

    public RelevanceCaseListItem mapToRelevanceCaseListItem(RelevanceCaseAggregate aggregate, RelevanceScore score) {
        return new RelevanceCaseListItem(
                aggregate.getId(),
                aggregate.getName(),
                aggregate.getLabels().stream().collect(Collectors.toMap(Label::key, Label::value)),
                score.value(),
                aggregate.listExpectedResults().size()
        );
    }

    public RelevanceCaseDto mapToRelevanceCaseDto(RelevanceCaseAggregate aggregate) {
        return new RelevanceCaseDto(
                aggregate.getId(),
                aggregate.getName(),
                aggregate.getLabels().stream().collect(Collectors.toMap(Label::key, Label::value)),
                new SearchQueryDto(
                        aggregate.getQuery().getType(),
                        aggregate.getQuery().getSearchTerm(),
                        aggregate.getQuery().getParameters()
                ),
                new OfflineMetricDto(aggregate.getMetric().getName(), aggregate.getMetric().getCutoff()),
                aggregate.listExpectedResults()
        );
    }
}
