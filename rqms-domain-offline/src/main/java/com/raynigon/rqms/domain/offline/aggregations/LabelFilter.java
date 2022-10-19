package com.raynigon.rqms.domain.offline.aggregations;

import com.raynigon.rqms.domain.offline.valueobjects.CombinationOperator;
import com.raynigon.rqms.domain.offline.valueobjects.Label;
import com.raynigon.rqms.domain.offline.valueobjects.SelectionCondition;

import java.util.Set;

public record LabelFilter(
        CombinationOperator operator,
        Set<SelectionCondition> conditions
) {

    public boolean matches(Set<Label> labels) {
        return switch (operator) {
            case AND -> conditions.stream().parallel().allMatch(it -> it.matches(labels));
            case OR -> conditions.stream().parallel().anyMatch(it -> it.matches(labels));
        };
    }
}
