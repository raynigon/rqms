package com.raynigon.rqms.api.integration.models;

import java.util.Map;

public record SearchQueryDto(
        String type,
        String searchTerm,
        Map<String, String> parameters
) {
}
