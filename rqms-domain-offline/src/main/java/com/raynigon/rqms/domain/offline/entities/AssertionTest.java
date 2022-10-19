package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AssertionTest {

    @NonNull
    private final UUID id;

    @NonNull
    private SearchQuery query;

    @NonNull
    private Set<AssertionCondition> conditions;
}
