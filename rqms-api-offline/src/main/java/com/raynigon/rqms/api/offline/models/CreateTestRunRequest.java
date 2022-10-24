package com.raynigon.rqms.api.offline.models;

import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;

public record CreateTestRunRequest(
        String searchEngine,
        LabelFilter filter,
        String description
) {


}
