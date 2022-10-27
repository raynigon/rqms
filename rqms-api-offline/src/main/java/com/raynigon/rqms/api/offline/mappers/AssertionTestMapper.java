package com.raynigon.rqms.api.offline.mappers;

import com.raynigon.rqms.api.offline.models.AssertionConditionDto;
import com.raynigon.rqms.api.offline.models.AssertionTestDto;
import com.raynigon.rqms.api.offline.models.SearchQueryDto;
import com.raynigon.rqms.domain.offline.aggregations.AssertionTestAggregate;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import org.springframework.stereotype.Component;

@Component
public class AssertionTestMapper {
    public AssertionTestDto mapToDto(AssertionTestAggregate assertionTest) {
        return new AssertionTestDto(
                assertionTest.getId(),
                new SearchQueryDto(
                        assertionTest.getQuery().getType(),
                        assertionTest.getQuery().getSearchTerm(),
                        assertionTest.getQuery().getParameters()
                ),
                assertionTest.listConditions().stream().map(this::mapToDto).toList()
        );
    }

    public AssertionConditionDto mapToDto(AssertionCondition condition) {
        return new AssertionConditionDto(condition.code());
    }
}
