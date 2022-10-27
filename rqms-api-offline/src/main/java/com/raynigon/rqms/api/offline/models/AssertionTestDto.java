package com.raynigon.rqms.api.offline.models;

import java.util.List;
import java.util.UUID;

public record AssertionTestDto(
        UUID id,
        SearchQueryDto query,
        List<AssertionConditionDto> conditions
) {
}
