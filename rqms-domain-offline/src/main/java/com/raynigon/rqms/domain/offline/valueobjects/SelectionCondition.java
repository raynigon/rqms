package com.raynigon.rqms.domain.offline.valueobjects;

import java.util.Set;
import java.util.stream.Stream;

public record SelectionCondition(
        LabelComparison comparison,
        String key,
        String value
) {

    public boolean matches(Set<Label> labels) {
        Stream<Label> stream = labels.stream().parallel();
        return switch (comparison) {
            case ALWAYS -> true;
            case NEVER -> false;
            case EQUALS -> stream.anyMatch(label -> label.key().equals(key) && label.value().equals(value));
            case NOT_EQUALS -> stream.noneMatch(label -> label.key().equals(key) && label.value().equals(value));
            case HAS_KEY -> stream.anyMatch(label -> label.key().equals(key));
            case HAS_VALUE -> stream.anyMatch(label -> label.value().equals(value));
            case CONTAINS_KEY -> stream.anyMatch(label -> label.key().contains(key));
            case CONTAINS_VALUE -> stream.anyMatch(label -> label.value().contains(value));
        };
    }
}
