package com.raynigon.rqms.infrastructure.search;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class BasicSearchQuery implements SearchQuery {

    public static final String TYPE = "default-basic-query";

    private final String searchTerm;
    private final Map<String, String> parameters;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getSearchTerm() {
        return searchTerm;
    }

    @Override
    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
}
