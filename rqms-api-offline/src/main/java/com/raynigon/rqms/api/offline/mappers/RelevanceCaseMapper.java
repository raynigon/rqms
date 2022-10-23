package com.raynigon.rqms.api.offline.mappers;

import com.raynigon.rqms.api.offline.models.RelevanceCaseListItem;
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
}
