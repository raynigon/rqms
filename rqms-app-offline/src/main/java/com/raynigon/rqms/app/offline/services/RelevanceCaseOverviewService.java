package com.raynigon.rqms.app.offline.services;

import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.factories.RelevanceCaseFactory;
import com.raynigon.rqms.domain.offline.valueobjects.RelevanceScore;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelevanceCaseOverviewService {

    private final RelevanceCaseFactory relevanceCaseFactory;

    public List<Pair<RelevanceCaseAggregate, RelevanceScore>> listRelevanceCases() {
        return relevanceCaseFactory.listAll()
                .stream()
                .map(relevanceCase -> Pair.of(relevanceCase, new RelevanceScore(1.0)))
                .collect(Collectors.toList());
    }
}
