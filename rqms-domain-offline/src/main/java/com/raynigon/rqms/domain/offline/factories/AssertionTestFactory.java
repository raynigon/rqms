package com.raynigon.rqms.domain.offline.factories;

import com.raynigon.rqms.domain.offline.aggregations.AssertionTestAggregate;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.entities.AssertionTest;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.repositories.AssertionTestRepository;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import com.raynigon.rqms.infrastructure.scripting.ScriptEvaluator;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssertionTestFactory {

    private final AssertionTestRepository repository;

    private final SearchQueryFactory queryFactory;

    private final ScriptEvaluator scriptEvaluator;

    public List<AssertionTestAggregate> findAll() {
        return repository.findAll().stream().map(this::toAggregate).toList();
    }

    private AssertionTestAggregate toAggregate(AssertionTest assertionTest) {
        return new AssertionTestAggregate(assertionTest, repository, queryFactory, scriptEvaluator);
    }

    public AssertionTestAggregate create(String type, String searchTerm, Map<String, String> parameters, List<String> conditions) {
        AssertionTest assertionTest = new AssertionTest(
                UUID.randomUUID(),
                new SearchQueryData(type, searchTerm, parameters),
                conditions.stream().map(AssertionCondition::new).collect(Collectors.toSet())
        );
        return toAggregate(repository.save(assertionTest));
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public AssertionTestAggregate findById(UUID id) {
        return repository.findById(id).map(this::toAggregate).orElse(null);
    }
}
