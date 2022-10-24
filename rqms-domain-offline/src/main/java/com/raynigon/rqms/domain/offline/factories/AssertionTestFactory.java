package com.raynigon.rqms.domain.offline.factories;

import com.raynigon.rqms.domain.offline.aggregations.AssertionTestAggregate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssertionTestFactory {
    public List<AssertionTestAggregate> findAll() {
        return List.of();
    }
}
