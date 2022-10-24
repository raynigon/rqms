package com.raynigon.rqms.domain.offline.entities;

import com.raynigon.rqms.domain.offline.helpers.AssertionConditionConverter;
import com.raynigon.rqms.domain.offline.helpers.SearchQueryData;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(schema = "offline", name = "assertion_tests")
@NoArgsConstructor
@AllArgsConstructor
public class AssertionTest {

    @Id
    @NonNull
    private UUID id;

    @NonNull
    @Embedded
    private SearchQueryData query;

    @NonNull
    @Convert(converter = AssertionConditionConverter.class)
    private Set<AssertionCondition> conditions;
}
